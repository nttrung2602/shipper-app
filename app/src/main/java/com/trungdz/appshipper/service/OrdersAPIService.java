package com.trungdz.appshipper.service;

import com.trungdz.appshipper.response.ListItemResponse;
import com.trungdz.appshipper.response.MessageResponse;
import com.trungdz.appshipper.response.UnconfirmedOrdersForShipperResponse;
import com.trungdz.appshipper.response.UndeliveredOrdersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OrdersAPIService {
    @GET("orders/ship")
    Call<UnconfirmedOrdersForShipperResponse> getAllOrderForShipper();

    @GET("orders")
    Call<UndeliveredOrdersResponse> getAllOrdersOfAShipperWithStatus();

    @GET("orders/receive/{id_order}")
    Call<MessageResponse> receiveOrder(@Path("id_order") int id_order);

    @GET("orders/detail/{id_order}")
    Call<ListItemResponse> getAllItemInOrder(@Path("id_order") int id_order);
}
