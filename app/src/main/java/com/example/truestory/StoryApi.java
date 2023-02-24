package com.example.truestory;
import com.example.truestory.models.StoriesResponse;
import com.example.truestory.models.StoryDetailsResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Query;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StoryApi {
    @GET("api/get_editions")
    Object loadEditions(@Query("version") String version, @Query("build") int build, @Query("platform") String platform);

    @GET("api/first")
    Call<StoriesResponse> loadStories(@Query("edition") String edition, @Query("limit") int limit, @Query("offset") int offset, @Query("version") String version, @Query("build") int build, @Query("platform") String platform);

    @GET("api/stories/{clusterId}")
    Call<StoryDetailsResponse> loadStoryBy(@Path("clusterId") String clusterId, @Query("version") String version, @Query("build") int build, @Query("platform") String platform);
    @GET("collage_stories/{clusterId}")
    Call<ResponseBody> loadPictureBy(@Path("clusterId") String clusterId);
}
