package es.home.example.wso2is.userstore.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.user.api.UserStoreManager;
import org.wso2.carbon.user.core.service.RealmService;
import es.home.example.wso2is.userstore.CustomUserStore10;
import lombok.NoArgsConstructor;

/**
 * This class implements the OSGI Service component for Custom User Store Manager.
 */

@NoArgsConstructor
@Component(name = "custom.user.store.manager.dscomponent", service = CustomUserStore10DSC.class,
    immediate = true)
public class CustomUserStore10DSC {

  private static final Log LOG = LogFactory.getLog(CustomUserStore10DSC.class);
  private static RealmService realmService;

  public static RealmService getRealmService() {
    return realmService;
  }

  @Activate
  protected void activate(final ComponentContext ctxt) {
    UserStoreManager customUSManager = new CustomUserStore10();
    ctxt.getBundleContext().registerService(UserStoreManager.class.getName(), customUSManager,
        null);
    if (LOG.isDebugEnabled()) {
      LOG.debug("CustomUserStore10 bundle activated successfully..");
    }
  }

  @Deactivate
  protected void deactivate(final ComponentContext ctxt) {
    if (LOG.isDebugEnabled()) {
      LOG.debug("CustomUserStore10 bundle is deactivated ");
    }
  }

  @Reference(name = "RealmService", service = RealmService.class,
      cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.DYNAMIC,
      unbind = "unsetRealmService")
  protected void setRealmService(final RealmService rlmService) {
    realmService = rlmService;
  }

  protected void unsetRealmService(RealmService realmService) {
    realmService = null;
  }
}
