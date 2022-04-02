package webservice;

import mockup.DatabaseMock;
import webinterface.DebugAccessPoint;

import javax.jws.WebService;

@WebService(endpointInterface = "webinterface.DebugAccessPoint")
public class DebugAccessPointImpl implements DebugAccessPoint {
    private DatabaseMock db;

    public DebugAccessPointImpl() {
        System.out.println("Creating DebugAccessPointImpl with mock DB.");
        db = DatabaseMock.getMockInstance();
    }


    @Override
    public DataWrapper getMemory() {
        return new DataWrapper(db.freelancerInfos, db.contractInfos, db.customerInfos);
    }
}
