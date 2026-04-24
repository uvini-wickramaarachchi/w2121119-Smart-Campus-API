import java.util.*;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {

    @GET
    public Collection<Room> getAll() {
        return DataStore.rooms.values();
    }

    @POST
    public Room create(Room room) {
        DataStore.rooms.put(room.getId(), room);
        return room;
    }

    @GET
    @Path("/{id}")
    public Room get(@PathParam("id") String id) {
        return DataStore.rooms.get(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        Room room = DataStore.rooms.get(id);
        if (room != null && !room.getSensorIds().isEmpty()) {
            throw new RoomNotEmptyException("Room has sensors!");
        }
        DataStore.rooms.remove(id);
    }
}
