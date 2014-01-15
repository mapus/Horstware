package com.nttdata.emea.devschool.vehicleordering.network;

import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.nttdata.emea.devschool.vehicleordering.entities.Order;
import com.nttdata.emea.devschool.vehicleordering.xml.impl.XMLVehicleOrderResponse;

public class VehicleOrderingAPI implements OnRestResponse {
	private static final String SERVER_HOSTNAME = "http://192.168.178.42:8080";;
	private static final String SERVER_REST_API = "vehicle-ordering/services/orders";
	private static VehicleOrderingAPI instance = new VehicleOrderingAPI();

	private String response = "";

	public static VehicleOrderingAPI getInstance() {
		return instance;
	}

	private VehicleOrderingAPI() {
	}

	@Override
	public synchronized void onResponse(String response) {
		this.response = response;
		notify();

	}

	@Override
	public void onError(Integer errorCode, String errorMessage) {
		this.response = errorMessage;
	}

	public synchronized List<Order> getOrders() {

		final OnRestResponse orr = this;
		Runnable task = new Runnable() {
			@Override
			public void run() {
				RestClient client = new RestClient(SERVER_HOSTNAME);
				client.addHeader("Content-Type", "application/xml");
				client.get(SERVER_REST_API, orr);
			}
		};
		new Thread(task).run();

		try {wait();}
		catch (InterruptedException ignored) {}
		
		String xml;
		if(response != null)
		{
			xml = response;
		}
		else
		{
			xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><orders><order><id>10</id><amount>6</amount><customer><id>1</id><firstName>Jon</firstName><lastName>Doe</lastName></customer><deliveryDate>2014-01-24T00:00:00+01:00</deliveryDate><model><id>6</id><description>Holy Cow, das Ding geht ab! Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.</description><imageUrl>http://www.bikeonlineshop.de/images/pukydreiradlillifee.jpg</imageUrl><name>Todestreter Treitausend</name><priceInEuroCent>10000</priceInEuroCent><type><id>4</id><name>Dreiraeder</name></type></model></order><order><id>11</id><amount>1</amount><customer><id>1</id><firstName>Jon</firstName><lastName>Doe</lastName></customer><deliveryDate>2014-01-24T00:00:00+01:00</deliveryDate><model><id>7</id><description>Da legts di nieder! Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.</description><imageUrl>http://www.kokua-shop.com/WebRoot/Store7/Shops/62501574/511A/120B/8AA8/526D/41C6/C0A8/28BB/A79B/LIKEaBIKE_midi_Dreirad_klein.jpg</imageUrl><name>Tripletastic</name><priceInEuroCent>9999</priceInEuroCent><type><id>4</id><name>Dreiraeder</name></type></model></order><order><id>12</id><amount>5</amount><customer><id>2</id><firstName>Jane</firstName><lastName>Dove</lastName></customer><deliveryDate>2014-01-24T00:00:00+01:00</deliveryDate><model><id>6</id><description>Holy Cow, das Ding geht ab! Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.</description><imageUrl>http://www.bikeonlineshop.de/images/pukydreiradlillifee.jpg</imageUrl><name>Todestreter Treitausend</name><priceInEuroCent>10000</priceInEuroCent><type><id>4</id><name>Dreiraeder</name></type></model></order><order><id>13</id><amount>2</amount><customer><id>2</id><firstName>Jane</firstName><lastName>Dove</lastName></customer><deliveryDate>2014-01-24T00:00:00+01:00</deliveryDate><model><id>8</id><description>Meh, nicht so knorke. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.</description><imageUrl>http://demos.appthemes.com/classipress/files/2012/06/656479.jpg</imageUrl><name>Gammelkanu</name><priceInEuroCent>10000000</priceInEuroCent><type><id>5</id><name>Speed-Boats</name></type></model></order><order><id>14</id><amount>4</amount><customer><id>3</id><firstName>Jane</firstName><lastName>Doe</lastName></customer><deliveryDate>2014-01-24T00:00:00+01:00</deliveryDate><model><id>7</id><description>Da legts di nieder! Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.</description><imageUrl>http://www.kokua-shop.com/WebRoot/Store7/Shops/62501574/511A/120B/8AA8/526D/41C6/C0A8/28BB/A79B/LIKEaBIKE_midi_Dreirad_klein.jpg</imageUrl><name>Tripletastic</name><priceInEuroCent>9999</priceInEuroCent><type><id>4</id><name>Dreiraeder</name></type></model></order><order><id>15</id><amount>3</amount><customer><id>3</id><firstName>Jane</firstName><lastName>Doe</lastName></customer><deliveryDate>2014-01-24T00:00:00+01:00</deliveryDate><model><id>9</id><description>Da schwimmt man schneller. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.</description><imageUrl>http://tzach.edublogs.org/files/2011/01/speedBoat-szx5d2.jpg</imageUrl><name>Bremser-Boye</name><priceInEuroCent>8000000</priceInEuroCent><type><id>5</id><name>Speed-Boats</name></type></model></order><order><id>16</id><amount>32</amount><customer><id>1</id><firstName>Jon</firstName><lastName>Doe</lastName></customer><deliveryDate>2014-01-24T00:00:00+01:00</deliveryDate><model><id>6</id><description>Holy Cow, das Ding geht ab! Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.</description><imageUrl>http://www.bikeonlineshop.de/images/pukydreiradlillifee.jpg</imageUrl><name>Todestreter Treitausend</name><priceInEuroCent>10000</priceInEuroCent><type><id>4</id><name>Dreiraeder</name></type></model></order><order><id>60</id><amount>6</amount><customer><id>51</id><firstName>Jon</firstName><lastName>Doe</lastName></customer><deliveryDate>2014-01-24T00:00:00+01:00</deliveryDate><model><id>56</id><description>Holy Cow, das Ding geht ab! Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.</description><imageUrl>http://www.bikeonlineshop.de/images/pukydreiradlillifee.jpg</imageUrl><name>Todestreter Treitausend</name><priceInEuroCent>10000</priceInEuroCent><type><id>54</id><name>Dreiraeder</name></type></model></order><order><id>61</id><amount>1</amount><customer><id>51</id><firstName>Jon</firstName><lastName>Doe</lastName></customer><deliveryDate>2014-01-24T00:00:00+01:00</deliveryDate><model><id>57</id><description>Da legts di nieder! Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.</description><imageUrl>http://www.kokua-shop.com/WebRoot/Store7/Shops/62501574/511A/120B/8AA8/526D/41C6/C0A8/28BB/A79B/LIKEaBIKE_midi_Dreirad_klein.jpg</imageUrl><name>Tripletastic</name><priceInEuroCent>9999</priceInEuroCent><type><id>54</id><name>Dreiraeder</name></type></model></order><order><id>62</id><amount>5</amount><customer><id>52</id><firstName>Jane</firstName><lastName>Dove</lastName></customer><deliveryDate>2014-01-24T00:00:00+01:00</deliveryDate><model><id>56</id><description>Holy Cow, das Ding geht ab! Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.</description><imageUrl>http://www.bikeonlineshop.de/images/pukydreiradlillifee.jpg</imageUrl><name>Todestreter Treitausend</name><priceInEuroCent>10000</priceInEuroCent><type><id>54</id><name>Dreiraeder</name></type></model></order><order><id>63</id><amount>2</amount><customer><id>52</id><firstName>Jane</firstName><lastName>Dove</lastName></customer><deliveryDate>2014-01-24T00:00:00+01:00</deliveryDate><model><id>58</id><description>Meh, nicht so knorke. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.</description><imageUrl>http://demos.appthemes.com/classipress/files/2012/06/656479.jpg</imageUrl><name>Gammelkanu</name><priceInEuroCent>10000000</priceInEuroCent><type><id>55</id><name>Speed-Boats</name></type></model></order><order><id>64</id><amount>4</amount><customer><id>53</id><firstName>Jane</firstName><lastName>Doe</lastName></customer><deliveryDate>2014-01-24T00:00:00+01:00</deliveryDate><model><id>57</id><description>Da legts di nieder! Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.</description><imageUrl>http://www.kokua-shop.com/WebRoot/Store7/Shops/62501574/511A/120B/8AA8/526D/41C6/C0A8/28BB/A79B/LIKEaBIKE_midi_Dreirad_klein.jpg</imageUrl><name>Tripletastic</name><priceInEuroCent>9999</priceInEuroCent><type><id>54</id><name>Dreiraeder</name></type></model></order><order><id>65</id><amount>3</amount><customer><id>53</id><firstName>Jane</firstName><lastName>Doe</lastName></customer><deliveryDate>2014-01-24T00:00:00+01:00</deliveryDate><model><id>59</id><description>Da schwimmt man schneller. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.</description><imageUrl>http://tzach.edublogs.org/files/2011/01/speedBoat-szx5d2.jpg</imageUrl><name>Bremser-Boye</name><priceInEuroCent>8000000</priceInEuroCent><type><id>55</id><name>Speed-Boats</name></type></model></order></orders>";
		}
		
		XMLVehicleOrderResponse res = null;
		Serializer serializer = new Persister();
		try {
			res = serializer.read(XMLVehicleOrderResponse.class, xml);

			for (Order o : res.getOrders()) {
				Log.d("Roland", o.getCustomer().getFirstName());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res.getOrders();

	}
}