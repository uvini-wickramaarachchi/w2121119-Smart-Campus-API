@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class DiscoveryResource {

    @GET
    public Map<String,Object> getInfo() {
        Map<String,Object> map = new HashMap<>();
        map.put("version","v1");

        Map<String,String> links = new HashMap<>();
        links.put("rooms","/api/v1/rooms");
        links.put("sensors","/api/v1/sensors");

        map.put("endpoints",links);
        return map;
    }
}