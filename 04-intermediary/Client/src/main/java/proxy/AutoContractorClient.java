package proxy;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import interfaces.*;
import util.Constants;

public class AutoContractorClient implements ClientProxy {

    private final IFreelancerAccessPoint freelancerProxy;
    private final IContractAccessPoint contractProxy;

    public AutoContractorClient() throws MalformedURLException {
        URL freelancerUrl = new URL(Constants.CONTRACTOR_FREELANCER_ENDPOINT + "?wsdl");
        QName freelancerQname = new QName(Constants.CONTRACTOR_ACCESS_POINT, "FreelancerAccessPointService");
        Service freelancerService = Service.create(freelancerUrl, freelancerQname);
        freelancerProxy = freelancerService.getPort(IFreelancerAccessPoint.class);

        URL contractUrl = new URL(Constants.CONTRACTOR_CONTRACT_ENDPOINT + "?wsdl");
        QName contractQname = new QName(Constants.CONTRACTOR_ACCESS_POINT, "ContractAccessPointService");
        Service contractService = Service.create(contractUrl, contractQname);
        contractProxy = contractService.getPort(IContractAccessPoint.class);
    }

    @Override
    public IFreelancerAccessPoint GetFreelancerProxy() {
        return freelancerProxy;
    }

    @Override
    public IContractAccessPoint GetContractProxy() {
        return contractProxy;
    }
}
