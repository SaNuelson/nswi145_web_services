package webinterface;

import common.ContractInfo;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.JAXBElement;

public interface ContractAccessPoint {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("by/{customerId}")
    int[] getSubmittedContracts(@PathParam("customerId") String customerId);

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("info/{contractId}")
    ContractInfo getContractInfo(@PathParam("contractId") String contractId);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response submitContract(ContractInfo contractInfo);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/assign/{contractId}/{freelancerId}")
    Response assignFreelancer(@PathParam("contractId") String contractId, @PathParam("freelancerId") String freelancerId);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/dismiss/{contractId}")
    Response dismissFreelancer(@PathParam("contractId") String contractId);
}
