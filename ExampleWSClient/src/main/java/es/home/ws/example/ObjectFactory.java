
package es.home.ws.example;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.home.ws.example package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetUsers_QNAME = new QName("http://example.ws.home.es/", "getUsers");
    private final static QName _UserXml_QNAME = new QName("http://example.ws.home.es/", "userXml");
    private final static QName _DespideResponse_QNAME = new QName("http://example.ws.home.es/", "despideResponse");
    private final static QName _Saludo_QNAME = new QName("http://example.ws.home.es/", "saludo");
    private final static QName _Saluda_QNAME = new QName("http://example.ws.home.es/", "saluda");
    private final static QName _GetUsersResponse_QNAME = new QName("http://example.ws.home.es/", "getUsersResponse");
    private final static QName _Despide_QNAME = new QName("http://example.ws.home.es/", "despide");
    private final static QName _SaludaResponse_QNAME = new QName("http://example.ws.home.es/", "saludaResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.home.ws.example
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Saluda }
     * 
     */
    public Saluda createSaluda() {
        return new Saluda();
    }

    /**
     * Create an instance of {@link DespideResponse }
     * 
     */
    public DespideResponse createDespideResponse() {
        return new DespideResponse();
    }

    /**
     * Create an instance of {@link Saludo }
     * 
     */
    public Saludo createSaludo() {
        return new Saludo();
    }

    /**
     * Create an instance of {@link GetUsers }
     * 
     */
    public GetUsers createGetUsers() {
        return new GetUsers();
    }

    /**
     * Create an instance of {@link UserXml }
     * 
     */
    public UserXml createUserXml() {
        return new UserXml();
    }

    /**
     * Create an instance of {@link SaludaResponse }
     * 
     */
    public SaludaResponse createSaludaResponse() {
        return new SaludaResponse();
    }

    /**
     * Create an instance of {@link Despide }
     * 
     */
    public Despide createDespide() {
        return new Despide();
    }

    /**
     * Create an instance of {@link GetUsersResponse }
     * 
     */
    public GetUsersResponse createGetUsersResponse() {
        return new GetUsersResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.ws.home.es/", name = "getUsers")
    public JAXBElement<GetUsers> createGetUsers(GetUsers value) {
        return new JAXBElement<GetUsers>(_GetUsers_QNAME, GetUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserXml }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.ws.home.es/", name = "userXml")
    public JAXBElement<UserXml> createUserXml(UserXml value) {
        return new JAXBElement<UserXml>(_UserXml_QNAME, UserXml.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DespideResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.ws.home.es/", name = "despideResponse")
    public JAXBElement<DespideResponse> createDespideResponse(DespideResponse value) {
        return new JAXBElement<DespideResponse>(_DespideResponse_QNAME, DespideResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Saludo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.ws.home.es/", name = "saludo")
    public JAXBElement<Saludo> createSaludo(Saludo value) {
        return new JAXBElement<Saludo>(_Saludo_QNAME, Saludo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Saluda }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.ws.home.es/", name = "saluda")
    public JAXBElement<Saluda> createSaluda(Saluda value) {
        return new JAXBElement<Saluda>(_Saluda_QNAME, Saluda.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.ws.home.es/", name = "getUsersResponse")
    public JAXBElement<GetUsersResponse> createGetUsersResponse(GetUsersResponse value) {
        return new JAXBElement<GetUsersResponse>(_GetUsersResponse_QNAME, GetUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Despide }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.ws.home.es/", name = "despide")
    public JAXBElement<Despide> createDespide(Despide value) {
        return new JAXBElement<Despide>(_Despide_QNAME, Despide.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaludaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.ws.home.es/", name = "saludaResponse")
    public JAXBElement<SaludaResponse> createSaludaResponse(SaludaResponse value) {
        return new JAXBElement<SaludaResponse>(_SaludaResponse_QNAME, SaludaResponse.class, null, value);
    }

}
