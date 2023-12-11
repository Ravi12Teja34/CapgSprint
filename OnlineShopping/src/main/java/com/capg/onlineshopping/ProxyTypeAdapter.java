/*package com.capg.onlineshopping;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class ProxyTypeAdapter extends TypeAdapter<Proxy> {
	
    @Override
    public void write(JsonWriter out, Proxy value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }

        // Implement serialization logic here
        out.beginObject();
        out.name("type").value(value.type().name());
        out.name("address").value(value.address().toString());
        // Add more properties as needed
        out.endObject();
    }

    @Override
    public Proxy read(JsonReader in) throws IOException {
        // Implement deserialization logic here
        in.beginObject();

        Proxy.Type type = null;
        String addressString = null;

        while (in.hasNext()) {
            String name = in.nextName();
            switch (name) {
                case "type":
                    type = Proxy.Type.valueOf(in.nextString());
                    break;
                case "address":
                    addressString = in.nextString();
                    break;
                // Add more cases for additional properties if needed
                default:
                    in.skipValue(); // Ignore unknown properties
                    break;
            }
        }

        in.endObject();

        // Create a Proxy instance using the read properties
        if (type != null && addressString != null) {
            String[] addressParts = addressString.split(":");
            if (addressParts.length == 2) {
                String host = addressParts[0].substring(1); // Removing leading "/"
                int port = Integer.parseInt(addressParts[1]);

                try {
                    // Make the type field accessible
                    Field typeField = Proxy.class.getDeclaredField("type");
                    typeField.setAccessible(true);

                    // Create a Proxy instance with the accessible type field
                    return new Proxy(type, new InetSocketAddress(host, port));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return null; // Return null if properties are missing or invalid
    }

    public static void main(String[] args) {
        // Example usage
        List<Proxy> proxies = new ArrayList<>();
        proxies.add(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.example.com", 8080)));

        // Create Gson instance with the custom TypeAdapter
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Proxy.class, new ProxyTypeAdapter())
                .create();

        // Serialize to JSON
        String json = gson.toJson(proxies);
        System.out.println("Serialized JSON: " + json);

        // Deserialize from JSON
        Type listType = new TypeToken<List<Proxy>>() {}.getType();
        List<Proxy> deserializedProxies = gson.fromJson(json, listType);
        System.out.println("Deserialized Proxies: " + deserializedProxies);
    }

}*/
