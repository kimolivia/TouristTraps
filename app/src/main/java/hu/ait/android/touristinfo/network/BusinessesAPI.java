package hu.ait.android.touristinfo.network;

import hu.ait.android.touristinfo.data.businesses.BusinessesResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by zhaozhaoxia on 12/5/17.
 */

public interface BusinessesAPI {
    @Headers("Authorization: Bearer 9GQa-MTm1gfDFJZST93za3BsiS2S4K0WC0vIbeAouS4zFi1qYxZFdrhv_1ZzV7UQc01DDxCwYdzEjupFS0CK1OCSdZESjHqNclIR8kkCvlD7qVRYXiwQEMfjGcYmWnYx")
    @GET("v3/businesses/search")
    Call<BusinessesResult> getBusinessesResult(
                                               @Query("term") String category,@Query("location") String location);
}