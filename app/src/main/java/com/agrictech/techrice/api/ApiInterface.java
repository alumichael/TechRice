package com.agrictech.techrice.api;




import com.agrictech.techrice.model.delivery.DeliverPost;
import com.agrictech.techrice.model.delivery.DeliveryRespHead;
import com.agrictech.techrice.model.fertilizer.FertResponseHead;
import com.agrictech.techrice.model.herbicide.HerbResponseHead;
import com.agrictech.techrice.model.land_info.LandInfoPost;
import com.agrictech.techrice.model.land_info.LandInfoRespHead;
import com.agrictech.techrice.model.login.LoginPost;
import com.agrictech.techrice.model.login.LoginResponseHead;
import com.agrictech.techrice.model.OnlyFarmerIDPost;
import com.agrictech.techrice.model.products.ProductResponseHead;
import com.agrictech.techrice.model.purchase.PurchasePost;
import com.agrictech.techrice.model.purchase.PurchaseRespHead;
import com.agrictech.techrice.model.register.RegisterPost;
import com.agrictech.techrice.model.register.RegisterResponseHead;
import com.agrictech.techrice.model.schedule_period.ScheduleResponseHead;
import com.agrictech.techrice.model.season_guild.ProcessResponseHead;
import com.agrictech.techrice.model.seed_require.SeedResponseHead;
import com.agrictech.techrice.model.transaction.HistoryHead;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiInterface {

    @FormUrlEncoded
    @POST("login/create")
    Call<LoginResponseHead> login(@Field("phone") String phone,
                                  @Field("password") String password);

    @FormUrlEncoded
    @POST("register/create")
    Call<RegisterResponseHead> register(@Field("firstname") String firstname,
                                        @Field("lastname") String lastname,
                                        @Field("email") String email,
                                        @Field("phone") String phone,
                                        @Field("password") String password,
                                        @Field("state") String state,
                                        @Field("lga") String lga);

    @FormUrlEncoded
    @POST("land/create")
    Call<LandInfoRespHead> land_info(@Field("land_area") double land_area,
                                     @Field("land_nature") String land_nature,
                                 @Field("farmer_id") int farmer_id);
    //Requirement
    @FormUrlEncoded
    @POST("required/seed")
    Call<SeedResponseHead> seed_require(@Field("farmer_id") int farmer_id);

    @FormUrlEncoded
    @POST("required/fertilizer")
    Call<FertResponseHead> fertilizer_require(@Field("farmer_id") int farmer_id);

    @FormUrlEncoded
    @POST("required/herbicide")
    Call<HerbResponseHead> herbicide_require(@Field("farmer_id") int farmer_id);

    //Request
    @FormUrlEncoded
    @POST("request/seed")
    Call<SeedResponseHead> seed_request(@Field("land_nature") String land_nature,
                                        @Field("land_area") String land_area);

    @FormUrlEncoded
    @POST("request/fertilizer")
    Call<FertResponseHead> fertilizer_request(@Field("land_nature") String land_nature,
                                              @Field("land_area") String land_area);

    @FormUrlEncoded
    @POST("request/herbicide")
    Call<HerbResponseHead> herbicide_request(@Field("land_nature") String land_nature,
                                             @Field("land_area") String land_area);

    @FormUrlEncoded
    @POST("schedule/calendar")
    Call<ScheduleResponseHead> schedule_calendar(@Field("farmer_id") int farmer_id);

    @FormUrlEncoded
    @POST("request/calendar")
    Call<ScheduleResponseHead> request_calendar(@Field("land_nature") String land_nature,
                                                @Field("state") String state);

    @FormUrlEncoded
    @POST("schedule/guide")
    Call<ProcessResponseHead> process(@Field("farmer_id") int farmer_id);

    @FormUrlEncoded
    @POST("request/guide")
    Call<ProcessResponseHead> process_request(@Field("land_nature") String land_nature,
                                              @Field("state") String state);

    @FormUrlEncoded
    @POST("purchase/products")
    Call<ProductResponseHead> product_list(@Field("farmer_id") int farmer_id);

    @FormUrlEncoded
    @POST("purchase/make")
    Call<PurchaseRespHead> buy_input(@Field("farmer_id") int farmer_id,
                                     @Field("reference") String reference,
                                     @Field("products") String products,
                                     @Field("amount") int amount);

    @FormUrlEncoded
    @POST("purchase/list")
    Call<HistoryHead> transact_history(@Field("farmer_id") int farmer_id);

    @FormUrlEncoded
    @POST("purchase/deliver")
    Call<DeliveryRespHead> delivery(@Field("farmer_id") int farmer_id,
                                    @Field("order_id") int order_id);




}
