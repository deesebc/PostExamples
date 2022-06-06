package es.home.example.wso2is.userstore;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.user.api.Properties;
import org.wso2.carbon.user.api.Property;
import org.wso2.carbon.user.api.RealmConfiguration;
import org.wso2.carbon.user.core.UserRealm;
import org.wso2.carbon.user.core.UserStoreException;
import org.wso2.carbon.user.core.claim.ClaimManager;
import org.wso2.carbon.user.core.common.AuthenticationResult;
import org.wso2.carbon.user.core.common.FailureReason;
import org.wso2.carbon.user.core.common.User;
import org.wso2.carbon.user.core.jdbc.UniqueIDJDBCUserStoreManager;
import org.wso2.carbon.user.core.profile.ProfileConfigurationManager;
import org.wso2.carbon.user.core.util.UserCoreUtil;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomUserStore10 extends UniqueIDJDBCUserStoreManager {

  private static final Log LOG = LogFactory.getLog(CustomUserStore10.class);

  private final static String CUS_ALG_NAME = "algorithm";
  private final static String CUS_ALG_DFLT_VAL = "BASE64";
  private final static String CUS_ALG_DIS_NAME = "Algorithm";
  private final static String CUS_ALG_DESC = "Algorithm for encrypt username";
  private final static String CUS_ALG_PROP_DESC = CUS_ALG_DIS_NAME + "#" + CUS_ALG_DESC;

  public CustomUserStore10(final RealmConfiguration realmConfig,
      final Map<String, Object> properties, final ClaimManager claimManager,
      final ProfileConfigurationManager profileManager, final UserRealm realm,
      final Integer tenantId) throws UserStoreException {
    super(realmConfig, properties, claimManager, profileManager, realm, tenantId);
    this.realmConfig = realmConfig;
  }

  @Override
  protected AuthenticationResult doAuthenticateWithUserName(final String userName,
      final Object credential) throws UserStoreException {
    LOG.debug("doAuthenticateWithUserName - init");
    LOG.debug("userName: " + userName);
    LOG.debug("credential: " + credential);
    AuthenticationResult authResult =
        new AuthenticationResult(AuthenticationResult.AuthenticationStatus.FAIL);

    boolean userPassValid = true;
    // CODE FROM WSO2 IS CORE - INIT
    // In order to avoid unnecessary db queries.
    if (!isValidUserName(userName)) {
      String reason = "Username validation failed.";
      if (LOG.isDebugEnabled()) {
        LOG.debug(reason);
      }
      authResult = getAuthenticationResult(reason);
      userPassValid = false;
    }

    if (UserCoreUtil.isRegistryAnnonymousUser(userName)) {
      String reason = "Anonymous user trying to login.";
      LOG.error(reason);
      authResult = getAuthenticationResult(reason);
      userPassValid = false;
    }

    if (!isValidCredentials(credential)) {
      String reason = "Password validation failed.";
      if (LOG.isDebugEnabled()) {
        LOG.debug(reason);
      }
      authResult = getAuthenticationResult(reason);
      userPassValid = false;
    }
    // CODE FROM WSO2 IS CORE - END

    if (userPassValid) {
      String algorithm = realmConfig.getUserStoreProperty(CUS_ALG_NAME);
      String sCredential = (String) credential;
      String myPwdEncripted = null;
      if ("BASE64".equals(algorithm)) {
        myPwdEncripted = Base64.getEncoder().encodeToString(userName.getBytes());
      }
      LOG.debug("myPwdEncripted: " + myPwdEncripted);
      if (sCredential.equalsIgnoreCase(myPwdEncripted)) {
        User user = getUser(getUserIDFromUserName(userName), userName);
        authResult = new AuthenticationResult(AuthenticationResult.AuthenticationStatus.SUCCESS);
        authResult.setAuthenticatedUser(user);
      } else {
        authResult = new AuthenticationResult(AuthenticationResult.AuthenticationStatus.FAIL);
        authResult.setFailureReason(new FailureReason("Password change required."));
      }
    }
    return authResult;
  }

  private AuthenticationResult getAuthenticationResult(final String reason) {
    AuthenticationResult authResult =
        new AuthenticationResult(AuthenticationResult.AuthenticationStatus.FAIL);
    authResult.setFailureReason(new FailureReason(reason));
    return authResult;
  }

  @Override
  public Properties getDefaultUserStoreProperties() {
    Properties properties = super.getDefaultUserStoreProperties();
    List<Property> mandatories =
        Stream.of(properties.getMandatoryProperties()).collect(Collectors.toList());
    // create new configuration property
    Property apiURL = new Property(CUS_ALG_NAME, CUS_ALG_DFLT_VAL, CUS_ALG_PROP_DESC, null);
    // add to mandatory properties
    mandatories.add(apiURL);

    properties.setMandatoryProperties(mandatories.stream().toArray(Property[]::new));
    return properties;
  }
}
