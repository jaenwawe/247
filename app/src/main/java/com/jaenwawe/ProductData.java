package com.jaenwawe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductData {

	List<Map<String,?>>productList;

	public ProductData(){
		String productName;
		double price;
		int unitsAvailable;
		int targetNumber;
		String weight;
		String aisn;
		String description;
		String url;
		productList = new ArrayList<Map<String,?>>();


		aisn = "b00hqwlhj8";
		weight = ".8 ounces";
		price = 17.99;
		productName = "8GB Flash Drive";
		url ="http://ecx.images-amazon.com/images/I/91UObPaaomL._SL1500_.jpg";
		description = "set of 10 - Enfain Bulk 8GB USB 2.0 Flash Drive";
		productList.add(createProduct(productName, R.drawable.usb, description, aisn, weight, price,url));

		aisn = "b0044w4hww";
		weight = "16 ounces";
		price = 7.03;
		productName = "Trident Sugar Free Gum, Spearmint";
		url ="http://ecx.images-amazon.com/images/I/51IGRrVKnML.SX334_SY500_CR,0,0,334,500_PIbundle-12,TopRight,0,0_SX334_SY500_CR,0,0,334,500_SH20_.jpg";
		description = "Trident Sugar Free Gum, Spearmint,18-Count (Pack of 12).";
		productList.add(createProduct(productName, R.drawable.gum, description, aisn, weight, price,url));

		aisn = "b000ypkody";
		weight = "3.4 pounds";
		price = 17.98;
		productName = "Cracker Jack";
		url ="http://ecx.images-amazon.com/images/I/91QF3Jtm7CL._SY550SX251_SY550_CR,0,0,251,550_PIbundle-25,TopRight,0,0_SX251_SY550_CR,0,0,251,550_SH20_.jpg";
		description = "Cracker Jack Original Singles, 1-Ounce Boxes (Pack of 25)";
		productList.add(createProduct(productName, R.drawable.popcorn, description, aisn, weight, price,url));

		aisn = "b004g4ikgm";
		weight = ".3 ounces";
		price = 10.49;
		productName = "Android Toy";
		url = "http://ecx.images-amazon.com/images/I/41S%2BCXqqtAL.jpg";
		description = "Dyzplastic Android Mini Collectible Figure, Standard Green";
		productList.add(createProduct(productName, R.drawable.android, description, aisn, weight, price, url));

		aisn = "b019fwwjcu";
		weight = "1.9 pounds";
		price = 17.99;
		productName = "Pencils";
		url = "http://ecx.images-amazon.com/images/I/71I0wXX09ML._SL1500_.jpg";
		description = "No 2 Yellow Wood Pencil Great School Art Supplies For Writing, Drawing & Sketching ";
		productList.add(createProduct(productName, R.drawable.pencil, description, aisn, weight, price, url));

		aisn = "B014I8SSD0";
		weight = "2.9 ounces";
		price = 5.49;
		productName = "HDMI Cord";
		url = "http://ecx.images-amazon.com/images/I/71bYwPvaHML._SL1500_.jpg";
		description = "High-Speed HDMI Cable - 6 Feet (Latest Standard)";
		productList.add(createProduct(productName, R.drawable.hmdicord, description, aisn, weight, price, url));


		aisn = "b01DF1BPR2";
		weight = "2.4 ounces";
		price = 12.99;
		productName = "Pens";
		url = "http://cdn.shopify.com/s/files/1/0116/6402/products/black-another-great-idea-pne_1024x1024.jpg?v=1403318850";
		description = "Business Gift Premium Gel Ink Roller Ball Pens, Fine Point, Black Ink, Dozen Box";
		productList.add(createProduct(productName, R.drawable.pen, description, aisn, weight, price, url));

		aisn = "b00u78nqho";
		weight = "12 ounces";
		price = 15.99;
		productName = "Colored Pencils";
		url = "http://ecx.images-amazon.com/images/I/61J8Dku7i7L._SL1000_.jpg";
		description = "8-color Colored Pencils/ Drawing Pencils for Sketch/Secret Garden Coloring Book";
		productList.add(createProduct(productName, R.drawable.colorpencils, description, aisn, weight, price, url));

		aisn = "b00jpay9lm";
		weight = "10.6 ounces";
		price = 11.41;
		productName = "Eraser";
		url = "http://ecx.images-amazon.com/images/I/81p21QOpCKL._SL1500_.jpg";
		description = "Sargent Art 36-1012 36 Count Eraser Best Buy Pack, Pink";
		productList.add(createProduct(productName, R.drawable.eraser, description, aisn, weight, price, url));


		aisn = "b8883701127";
		weight = "16 ounces";
		price = 12.34;
		productName = "Moleskine Cahier Journal";
		url = "http://ecx.images-amazon.com/images/I/61Fy3lS1PpL.jpg";
		description = " (Set of 3), Large, Ruled, Kraft Brown, Soft Cover (5 x 8.25): set of 3 Ruled Journals";
		productList.add(createProduct(productName, R.drawable.journal, description, aisn, weight, price, url));


	}

	public List<Map<String, ?>> getProductList() {
		return productList;
	}

	public int getSize() {
		return productList.size();
	}

	public HashMap getItem(int i) {
		if (i >= 0 && i < productList.size()) {
			return (HashMap) productList.get(i);
		} else return null;
	}

	private HashMap createProduct(String name, int image, String description, String aisn,
								  String weight, double price, String url) {
		HashMap product = new HashMap();
		product.put("image",image);
		product.put("name", name);
		product.put("description", description);
		product.put("AISN", aisn);
		product.put("weight",weight);
		product.put("price",price);
		product.put("url", url);
		product.put("selection",false);
		return product;
	}

	public void addItem(int position) {

		HashMap item =  (HashMap) productList.get(position);
		HashMap cloned = (HashMap) item.clone();
		productList.add((position + 1), cloned);
	}

	public void removeItem(int position) {
		productList.remove(position);
	}
	public int findFirst(String query) {
		int i = productList.size();
		for(i = 0;  i <(productList.size()); i++) {
			String mData = (String) productList.get(i).get("name");
			if (mData.toLowerCase().contains(query.toLowerCase()))
				return i;
		}
		return i;
	}

}