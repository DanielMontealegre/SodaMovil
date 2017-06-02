package com.example.usuario.sodamovil.Entidades;

/**
 * Created by Danel on 6/1/2017.
 */

import android.widget.Filter;

import com.example.usuario.sodamovil.BaseDeDatos.DataBase;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import android.content.Context;
import android.widget.Filter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DataHelper {

    private static List<RestauranteSuggestion> arrayRestauranteSuggestions =
            new ArrayList<>();


    public interface OnFindRestauranteListener {
        void onResults(List<RestauranteSuggestion> results);
    }
    public interface OnFindSuggestionsListener {
        void onResults(List<RestauranteSuggestion> results);
    }

    public static List<RestauranteSuggestion> getHistory(Context context, int count) {

        List<RestauranteSuggestion> suggestionList = new ArrayList<>();
        RestauranteSuggestion restauranteSuggestion;
        for (int i = 0; i < arrayRestauranteSuggestions.size(); i++) {
            restauranteSuggestion = arrayRestauranteSuggestions.get(i);
            restauranteSuggestion.setIsHistory(true);
            suggestionList.add(restauranteSuggestion);
            if (suggestionList.size() == count) {
                break;
            }
        }
        return suggestionList;
    }

    public static void resetSuggestionsHistory() {
        for (RestauranteSuggestion restauranteSuggestion : arrayRestauranteSuggestions) {
            restauranteSuggestion.setIsHistory(false);
        }
    }

    public static void findSuggestions(Context context, String query, final int limit, final long simulatedDelay,
                                       final OnFindSuggestionsListener listener) {
        new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                try {
                    Thread.sleep(simulatedDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                DataHelper.resetSuggestionsHistory();
                List<RestauranteSuggestion> suggestionList = new ArrayList<>();
                if (!(constraint == null || constraint.length() == 0)) {

                    for (RestauranteSuggestion suggestion : arrayRestauranteSuggestions) {
                        if (suggestion.getBody().toUpperCase()
                                .startsWith(constraint.toString().toUpperCase())) {

                            suggestionList.add(suggestion);
                            if (limit != -1 && suggestionList.size() == limit) {
                                break;
                            }
                        }
                    }
                }

                FilterResults results = new FilterResults();
                Collections.sort(suggestionList, new Comparator<RestauranteSuggestion>() {
                    @Override
                    public int compare(RestauranteSuggestion lhs, RestauranteSuggestion rhs) {
                        return lhs.getIsHistory() ? -1 : 0;
                    }
                });
                results.values = suggestionList;
                results.count = suggestionList.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (listener != null) {
                    listener.onResults((List<RestauranteSuggestion>) results.values);
                }
            }
        }.filter(query);

    }

    public static void findRestaurante(String query, final OnFindRestauranteListener listener) {
        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {


                List<RestauranteSuggestion> suggestionList = new ArrayList<>();

                if (!(constraint == null || constraint.length() == 0)) {

                    for (RestauranteSuggestion restauranteSuggestion : arrayRestauranteSuggestions) {
                        if (restauranteSuggestion.getBody().toUpperCase()
                                .startsWith(constraint.toString().toUpperCase())) {
                            suggestionList.add(restauranteSuggestion);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = suggestionList;
                results.count = suggestionList.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (listener != null) {
                    listener.onResults((List<RestauranteSuggestion>) results.values);
                }
            }
        }.filter(query);
    }



    public static void inicializarRestaurantes(){
        final DataBase db= DataBase.getInstance();
        db.getmDatabaseReference().child("Restaurantes_Todos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                clearRestaurantes();
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    Restaurante restaurante = postSnapshot.getValue(Restaurante.class);
                    arrayRestauranteSuggestions.add(new RestauranteSuggestion(restaurante.getNombre(),restaurante.getCodigo(),restaurante.getDescripcion()));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }

    public static void clearRestaurantes(){
        arrayRestauranteSuggestions.clear();
    }

    /*
    private static void initColorWrapperList(Context context) {

        if (sColorWrappers.isEmpty()) {
            String jsonString = loadJson(context);
            sColorWrappers = deserializeColors(jsonString);
        }
    }

    private static String loadJson(Context context) {

        String jsonString;

        try {
            InputStream is = context.getAssets().open(COLORS_FILE_NAME);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return jsonString;
    }

    private static List<ColorWrapper> deserializeColors(String jsonString) {

        Gson gson = new Gson();

        Type collectionType = new TypeToken<List<ColorWrapper>>() {
        }.getType();
        return gson.fromJson(jsonString, collectionType);
    }
*/
}
