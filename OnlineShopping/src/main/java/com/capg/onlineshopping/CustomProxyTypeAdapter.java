package com.capg.onlineshopping;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class CustomProxyTypeAdapter extends TypeAdapter<Proxy> {
	
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

	                return new Proxy(type, new InetSocketAddress(host, port));
	            }
	        }

	        return null; // Return null if properties are missing or invalid
	    }
}
