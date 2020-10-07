package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        String mainName = "", placeOfOrigin = "", description = "", image = "";
        List<String> ingredientsList = new ArrayList<>(), alsoKnownAsList = new ArrayList<>();

        if (json == null)
            return null;
        else {
            try {

                JSONObject jsonSandwich = new JSONObject(json);
                JSONObject jsonSandwichName = jsonSandwich.getJSONObject("name");

                mainName = jsonSandwichName.optString("mainName");
                JSONArray alsoKnownAsArray = jsonSandwichName.optJSONArray("alsoKnownAs");

                placeOfOrigin = jsonSandwich.optString("placeOfOrigin");
                description = jsonSandwich.optString("description");
                image = jsonSandwich.optString("image");

                JSONArray ingredientsArray = jsonSandwich.optJSONArray("ingredients");

                if (ingredientsArray != null)
                    for (int i = 0; i < ingredientsArray.length(); i++) {
                        ingredientsList.add(ingredientsArray.getString(i));
                    }
                if (alsoKnownAsArray != null)
                    for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                        alsoKnownAsList.add(alsoKnownAsArray.getString(i));
                    }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);
    }
}
