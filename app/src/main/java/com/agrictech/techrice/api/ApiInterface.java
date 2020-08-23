package com.agrictech.techrice.api;




import com.agrictech.techrice.model.delivery.DeliverPost;
import com.agrictech.techrice.model.delivery.DeliveryRespHead;
import com.agrictech.techrice.model.fertilizer.FertResponseHead;
import com.agrictech.techrice.model.herbicide.HerbResponseHead;
import com.agrictech.techrice.model.land_info.LandInfoPost;
import com.agrictech.techrice.model.land_info.LandInfoRespHead;
import com.agrictech.techrice.model.login.LoginPost;
import com.agrictech.techrice.model.login.LoginResponseHead;
import com.agrictech.techrice.model.onlyFarmerIDPost;
import com.agrictech.techrice.model.products.ProductResponseHead;
import com.agrictech.techrice.model.purchase.PurchasePost;
import com.agrictech.techrice.model.purchase.PurchaseRespHead;
import com.agrictech.techrice.model.register.RegisterPost;
import com.agrictech.techrice.model.register.RegisterResponseHead;
import com.agrictech.techrice.model.schedule_period.ScheduleResponseHead;
import com.agrictech.techrice.model.season_guild.ProcessResponseHead;
import com.agrictech.techrice.model.seed_require.SeedResponseHead;
import com.agrictech.techrice.model.transaction.HistoryHead;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiInterface {

    @POST("login/create")
    Call<LoginResponseHead> login(@Body LoginPost loginPost);

    @POST("register/create")
    Call<RegisterResponseHead> register(@Body RegisterPost registerPost);

    @POST("land/create")
    Call<LandInfoRespHead> land_info(@Body LandInfoPost landInfoPost);

    @POST("required/seed")
    Call<SeedResponseHead> seed_require(@Body onlyFarmerIDPost onlyFarmerIDPost);

    @POST("required/fertilizer")
    Call<FertResponseHead> fertilizer_require(@Body onlyFarmerIDPost onlyFarmerIDPost);

    @POST("required/herbicide")
    Call<HerbResponseHead> herbicide_require(@Body onlyFarmerIDPost onlyFarmerIDPost);

    @POST("schedule/calendar")
    Call<ScheduleResponseHead> schedule_calendar(@Body onlyFarmerIDPost onlyFarmerIDPost);

    @POST("schedule/guide")
    Call<ProcessResponseHead> process(@Body onlyFarmerIDPost onlyFarmerIDPost);

    @POST("purchase/products")
    Call<ProductResponseHead> product_list(@Body onlyFarmerIDPost onlyFarmerIDPost);

    @POST("purchase/make")
    Call<PurchaseRespHead> buy_input(@Body PurchasePost purchasePost);

    @POST("purchase/list")
    Call<HistoryHead> transact_history(@Body onlyFarmerIDPost onlyFarmerIDPost);

    @POST("purchase/deliver")
    Call<DeliveryRespHead> delivery(@Body DeliverPost deliverPost);






}
