package com.example.truestory;
import retrofit2.Call;
import retrofit2.http.Query;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StoryApi {
    @GET("get_editions")
    Object loadEditions(@Query("version") String version, @Query("build") int build, @Query("platform") String platform);

    @GET("first")
    Call<StoriesResponse> loadStories(@Query("edition") String edition, @Query("limit") int limit, @Query("offset") int offset, @Query("version") String version, @Query("build") int build, @Query("platform") String platform);

    @GET("stories/{clusterId}")
     Object loadStoryBy(@Path("clusterId") String clusterId, @Query("version") String version, @Query("build") int build, @Query("platform") String platform);
}
