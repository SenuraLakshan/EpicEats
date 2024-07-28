using ItemDetailsService.Models;
using Microsoft.EntityFrameworkCore;

namespace ItemDetailsService.DataAccess;

public class ItemContext : DbContext
{
    public ItemContext(DbContextOptions<ItemContext> options) : base(options) { }
    public DbSet<Item> MenuItems { get; set; }
}