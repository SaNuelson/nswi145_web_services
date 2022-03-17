package com.contractor.shared;

import javax.xml.namespace.QName;

public class Constants {
    public static final String CONTRACTOR_ACCESS_POINT = "http://interfaces/";
    public static final String CONTRACTOR_FREELANCER_ENDPOINT = "http://127.0.0.1:8000/freelancers";
    public static final String CONTRACTOR_CONTRACT_ENDPOINT = "http://127.0.0.1:8000/contracts";
    public static final String SERVLET_NAMESPACE_URI = "http://servlet.privacy/";

    public static class QNames {
        public static final QName GET_BY_JOB_TYPE = new QName(CONTRACTOR_ACCESS_POINT, "GetByJobType");
        public static final QName GET_BY_JOB_TYPE_RES = new QName(CONTRACTOR_ACCESS_POINT, "GetByJobTypeResponse");
        public static final QName GET_FLC_INFO = new QName(CONTRACTOR_ACCESS_POINT, "GetFreelancerInfo");
        public static final QName GET_FLC_INFO_RES = new QName(CONTRACTOR_ACCESS_POINT, "GetFreelancerInfoResponse");
        public static final QName ASSIGN_FLC = new QName(CONTRACTOR_ACCESS_POINT, "AssignFreelancer");
        public static final QName ASSIGN_FLC_RES = new QName(CONTRACTOR_ACCESS_POINT, "AssignFreelancerResponse");
        public static final QName DISMISS_FLC = new QName(CONTRACTOR_ACCESS_POINT, "DismissFreelancer");
        public static final QName DISMISS_FLC_RES = new QName(CONTRACTOR_ACCESS_POINT, "DismissFreelancerResponse");

        public static final QName FLC_INFO = new QName("freelancerInfo");
        public static final QName FLC_ID = new QName("freelancerId");
        public static final QName ID = new QName("id");
        public static final QName NAME = new QName("name");
        public static final QName JOB_TYPE = new QName("jobType");

        public static final QName CONTRACT_ID = new QName("contractId");

        public static final QName SET_STATE = new QName(CONTRACTOR_ACCESS_POINT, "SetState");
        public static final QName NEW_STATE = new QName("newState");

        public static final QName SERVLET_PRIVACY = new QName(SERVLET_NAMESPACE_URI, "privacy");
        public static final QName LEVEL = new QName("level");
    }
}
