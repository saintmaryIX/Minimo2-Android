package edu.upc.dsa_android_DriveNdodge.api;

import java.util.List;

import edu.upc.dsa_android_DriveNdodge.models.EventUser;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EventService {

    @POST("/v1/events/xmas/register")
    Call<Void> registerToEvent(@Body String username);

    @GET("/v1/events/xmas/users")
    Call<List<EventUser>> getEventUsers();
}
