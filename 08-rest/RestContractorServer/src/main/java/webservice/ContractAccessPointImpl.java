package webservice;

import common.ContractInfo;
import common.FreelancerInfo;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.xml.bind.JAXBElement;
import mockup.DatabaseMock;
import webinterface.ContractAccessPoint;

import java.util.Optional;

@Path("contracts")
public class ContractAccessPointImpl implements ContractAccessPoint {
    private DatabaseMock db;

    @Context
    UriInfo uriInfo;

    public ContractAccessPointImpl() {
        System.out.println("Creating ContractAccessPointImpl with mock DB.");
        db = DatabaseMock.getMockInstance();
    }


    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    @Path("/by/{customerId}")
    public int[] getSubmittedContracts(@PathParam("customerId") String customerId) {
        int cId;
        try {
            cId = Integer.parseInt(customerId);
        }
        catch (NumberFormatException nfe) {
            return null;
        }

        return db.contractInfos.stream()
                .filter(c -> c.getCustomerId() == cId)
                .mapToInt(c -> c.getId())
                .toArray();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    @Path("/info/{contractId}")
    public ContractInfo getContractInfo(@PathParam("contractId") String contractId) {
        int cId;
        try {
            cId = Integer.parseInt(contractId);
        }
        catch (NumberFormatException nfe) {
            return null;
        }

        return db.contractInfos.stream()
                .filter(c -> c.getId() == cId)
                .findFirst()
                .orElse(null);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response submitContract(ContractInfo contractInfo) {
        db.contractInfos.add(contractInfo);
        return Response
                .created(uriInfo.getAbsolutePath())
                .entity(contractInfo)
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/assign/{contractId}/{freelancerId}")
    @Override
    public Response assignFreelancer(@PathParam("contractId") String contractId,@PathParam("freelancerId") String freelancerId) {
        int cId;
        int fId;
        try {
            cId = Integer.parseInt(contractId);
            fId = Integer.parseInt(freelancerId);
        }
        catch(NumberFormatException nfe) {
            return Response
            		.status(Response.Status.BAD_REQUEST)
            		.entity("Either contract ID or freelancer ID are not valid integers.")
            		.build();
        }

        Optional<ContractInfo> maybeContract = db.contractInfos.stream().filter(c -> c.getId() == cId).findFirst();
        if (!maybeContract.isPresent())
            return Response
            		.status(Response.Status.BAD_REQUEST)
            		.entity("Contract with such ID does not exist.")
            		.build();

        ContractInfo contract = maybeContract.get();
        if (contract.getFreelancerId() >= FreelancerInfo.ID_MIN)
            return Response
            		.status(Response.Status.BAD_REQUEST)
            		.entity("Contract is already assigned.")
            		.build();

        if (db.freelancerInfos.stream().noneMatch(f -> f.getId() == fId))
        	return Response
            		.status(Response.Status.BAD_REQUEST)
            		.entity("Freelance with such ID does not exist.")
            		.build();
        
        contract.setFreelancerId(fId);
        return Response
        		.ok()
        		.entity(contract)
        		.build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/dismiss/{contractId}")
    @Override
    public Response dismissFreelancer(@PathParam("contractId") String contractId) {
        int cId;
        try {
            cId = Integer.parseInt(contractId);
        }
        catch (NumberFormatException nfe) {
            return Response
            		.status(Response.Status.BAD_REQUEST)
            		.entity("Provided contract ID is not a valid integer.")
            		.build();
        }

        Optional<ContractInfo> maybeContract = db.contractInfos.stream().filter(c -> c.getId() == cId).findFirst();
        if (!maybeContract.isPresent())
            return Response
            		.status(Response.Status.BAD_REQUEST)
            		.entity("No contract with such ID exists.")
            		.build();

        ContractInfo contract = maybeContract.get();
        if (contract.getFreelancerId() < FreelancerInfo.ID_MIN)
            return Response
            		.status(Response.Status.BAD_REQUEST)
            		.entity("Contract does not have any freelancer assigned.")
            		.build();

        contract.setFreelancerId(0);
        return Response
        		.ok()
        		.entity(contract)
        		.build();
    }
}
