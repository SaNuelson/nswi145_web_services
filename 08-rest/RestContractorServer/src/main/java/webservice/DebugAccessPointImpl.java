package webservice;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import mockup.DatabaseMock;
import webinterface.DebugAccessPoint;

@Path("debug")
public class DebugAccessPointImpl implements DebugAccessPoint {
    private DatabaseMock db;

    public DebugAccessPointImpl() {
        System.out.println("Creating DebugAccessPointImpl with mock DB.");
        db = DatabaseMock.getMockInstance();
    }

    @GET
    @Override
    public DataWrapper getMemory() {
        return new DataWrapper(db.freelancerInfos, db.contractInfos, db.customerInfos);
    }
}
