package webinterface;

import common.ContractInfo;
import common.CustomerInfo;
import common.FreelancerInfo;

import java.util.List;

public interface DebugAccessPoint {

    public DataWrapper getMemory();


    public static class DataWrapper {
        private List<FreelancerInfo> freelancerInfos;
        private List<ContractInfo> contractInfos;
        private List<CustomerInfo> customerInfos;

        public DataWrapper() {}

        public DataWrapper(List<FreelancerInfo> freelancerInfos, List<ContractInfo> contractInfos, List<CustomerInfo> customerInfos) {
            this.freelancerInfos = freelancerInfos;
            this.contractInfos = contractInfos;
            this.customerInfos = customerInfos;
        }

        public List<FreelancerInfo> getFreelancerInfos() {
            return freelancerInfos;
        }

        public void setFreelancerInfos(List<FreelancerInfo> freelancerInfos) {
            this.freelancerInfos = freelancerInfos;
        }

        public List<ContractInfo> getContractInfos() {
            return contractInfos;
        }

        public void setContractInfos(List<ContractInfo> contractInfos) {
            this.contractInfos = contractInfos;
        }

        public List<CustomerInfo> getCustomerInfos() {
            return customerInfos;
        }

        public void setCustomerInfos(List<CustomerInfo> customerInfos) {
            this.customerInfos = customerInfos;
        }
    }

}
