package co.example.form;

import java.util.List;

/**
 * 商品を追加する際に使用するフォーム.
 * 
 * @author iidashuhei
 *
 */
public class OrderItemForm {

	/* ItemID */
	private String itemId;
	/* 数量 */
	private String quantity;
	/* サイズ */
	private Character size;
	/* 注文商品リスト */
	private List<Integer> orderToppingList;

	@Override
	public String toString() {
		return "OrderItemForm [itemId=" + itemId + ", quantity=" + quantity + ", size=" + size + ", orderToppingList="
				+ orderToppingList + "]";
	}

	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the size
	 */
	public Character getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Character size) {
		this.size = size;
	}

	/**
	 * @return the orderToppingList
	 */
	public List<Integer> getOrderToppingList() {
		return orderToppingList;
	}

	/**
	 * @param orderToppingList the orderToppingList to set
	 */
	public void setOrderToppingList(List<Integer> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

}
