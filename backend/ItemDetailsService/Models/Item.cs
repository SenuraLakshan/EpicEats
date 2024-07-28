namespace ItemDetailsService.Models;

public class Item
{
    public int ItemId { get; set; }
    public int RestaurantId { get; set; }
    public string Name { get; set; }
    public int Price { get; set; }
    public string Description { get; set; }
}