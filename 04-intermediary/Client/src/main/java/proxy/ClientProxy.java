package proxy;

import interfaces.IContractAccessPoint;
import interfaces.IFreelancerAccessPoint;

public interface ClientProxy {

    public IFreelancerAccessPoint GetFreelancerProxy();
    public IContractAccessPoint GetContractProxy();

}
