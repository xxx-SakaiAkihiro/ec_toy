package co.example.domain;

import java.util.List;

/**
 * 注文商品情報を表すドメイン.
 * 
 * @author sakai
 *
 */
public class OrderItem {

	/** 注文商品ID */
	private Integer id;
	/** 商品ID */
	private Integer itemId;
	/** 注文ID */
	private Integer orderId;
	/** 数量 */
	private Integer quantity;
	/** サイズ */
	private Character size;
	/** 商品 */
	private Item item;
	/** 注文トッピング情報 */
	private List<OrderTopping> orderToppingList;

	/**
	 * （トッピング＋商品） ×数量の値段
	 * 
	 * @return
	 */
	public int getSubTotal() {
		int subtotal = 0;
		int totalTopping = 0;
		// トッピング+商品 Mの価格×数量
		if (size.equals('M')) {
			for (OrderTopping topping : orderToppingList) {
				totalTopping += topping.getTopping().getPriceM();
			}
			subtotal = (totalTopping + item.getPriceM()) * quantity;
			// トッピング Lの価格×数量
		} else {
			for (OrderTopping topping : orderToppingList) {
				totalTopping += topping.getTopping().getPriceL();
			}
			subtotal += (totalTopping + item.getPriceL()) * quantity;
		}
		return subtotal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Character getSize() {
		return size;
	}

	public void setSize(Character size) {
		this.size = size;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<OrderTopping> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<OrderTopping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + "]";
	}

}
