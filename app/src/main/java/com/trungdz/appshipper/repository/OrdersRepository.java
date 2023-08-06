package com.trungdz.appshipper.repository;

import android.util.Log;

import com.trungdz.appshipper.service.model.Item;
import com.trungdz.appshipper.service.model.Order;
import com.trungdz.appshipper.network.APIClient;
import com.trungdz.appshipper.response.ListItemResponse;
import com.trungdz.appshipper.response.MessageResponse;
import com.trungdz.appshipper.response.UnconfirmedOrdersForShipperResponse;
import com.trungdz.appshipper.response.UndeliveredOrdersResponse;
import com.trungdz.appshipper.service.OrdersAPIService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrdersRepository {

    OrdersAPIService ordersAPIService;

    public OrdersRepository() {
        Retrofit retrofit = APIClient.getRetrofit();
        ordersAPIService = retrofit.create(OrdersAPIService.class);
    }

    public void getAllOrderForShipper(IResponse<List<Order>> callback) {
        ordersAPIService.getAllOrderForShipper().enqueue(new Callback<UnconfirmedOrdersForShipperResponse>() {
            @Override
            public void onResponse(Call<UnconfirmedOrdersForShipperResponse> call, Response<UnconfirmedOrdersForShipperResponse> response) {

                callback.onResponse(response.body().getItemList());
            }

            @Override
            public void onFailure(Call<UnconfirmedOrdersForShipperResponse> call, Throwable t) {

            }
        });
    }

    public void getAllOrdersOfAShipperWithStatus(int status, IResponse<List<Order>> callback) {
        ordersAPIService.getAllOrdersOfAShipperWithStatus().enqueue(new Callback<UndeliveredOrdersResponse>() {
            @Override
            public void onResponse(Call<UndeliveredOrdersResponse> call, Response<UndeliveredOrdersResponse> response) {
                if(response.isSuccessful()){
                    List<Order> orderList = new ArrayList<>();
                    UndeliveredOrdersResponse unconfirmedOrdersForShipperResponse = response.body();
                    List<Order> orderListFromResponse = unconfirmedOrdersForShipperResponse.getOrderList();

                    int statusOfOrder;
                    Order order;
                    for (int i = 0; i < orderListFromResponse.size(); i++) {
                        order = orderListFromResponse.get(i);
                        statusOfOrder = order.getStatus();
                        if (statusOfOrder == status) {
                            orderList.add(order);
                        }
                    }

                    callback.onResponse(orderList);
                }else {
                    try {
                        callback.onError(response.errorBody().string());
                    } catch (IOException e) {
                        Log.d("Error in getAllOrdersOfAShipperWithStatus","AAAA");
                        throw new RuntimeException(e);
                    }
                }

            }

            @Override
            public void onFailure(Call<UndeliveredOrdersResponse> call, Throwable t) {

            }
        });
    }

    public void receiveOrder(int id_order, IResponse<String> callback) {
        ordersAPIService.receiveOrder(id_order).enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()) {
//                    boolean receivedSuccessfully = true;
//                    boolean confirmedSuccessfully = true;
                    String message=response.body().getMessage();
//
//                    if(message.equals("Nhận đơn thành công!"))
//                        callback.onResponse(receivedSuccessfully);
//                    else if (message.equals("Đơn hàng giao thành công!")) {
//                        callback.onResponse(confirmedSuccessfully);
//                    }
                    callback.onResponse(message);
                }else{
                    try {
                        callback.onError(response.errorBody().string());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {

            }
        });
    }

    public void getAllItemInOrder(int id_oder, IResponse<List<Item>> callback) {
        ordersAPIService.getAllItemInOrder(id_oder).enqueue(new Callback<ListItemResponse>() {
            @Override
            public void onResponse(Call<ListItemResponse> call, Response<ListItemResponse> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(response.body().getItemList());
                }else{
                    try {
                        callback.onError(response.errorBody().string());
                    } catch (IOException e) {
                        Log.d("Catch in getAllItemInOrder","1111111");
                        throw new RuntimeException(e);
                    }
                }

            }

            @Override
            public void onFailure(Call<ListItemResponse> call, Throwable t) {

            }
        });
    }

    public interface IResponse<T> {
        void onResponse(T data);
        void onError(String message);
    }
}
