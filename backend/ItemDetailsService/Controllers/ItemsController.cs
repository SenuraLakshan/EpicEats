using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using ItemDetailsService.DataAccess;
using ItemDetailsService.Models;
using Microsoft.EntityFrameworkCore;

namespace ItemDetailsService.Controllers;

[Route("api/[controller]")]
[ApiController]
public class ItemsController : ControllerBase
{
    private readonly ItemContext _context;

    public ItemsController(ItemContext context)
    {
        _context = context;
    }

    [HttpGet]
    public async Task<ActionResult<IEnumerable<Item>>> GetMenuItems()
    {
        return await _context.MenuItems.ToListAsync();
    }

    [HttpGet("{id}")]
    public async Task<ActionResult<Item>> GetMenuItem(int id)
    {
        var menuItem = await _context.MenuItems.FindAsync(id);

        if (menuItem == null)
        {
            return NotFound();
        }

        return menuItem;
    }

    [HttpPost]
    public async Task<ActionResult<Item>> PostMenuItem(Item menuItem)
    {
        _context.MenuItems.Add(menuItem);
        await _context.SaveChangesAsync();

        return CreatedAtAction(nameof(GetMenuItem), new { id = menuItem.ItemId }, menuItem);
    }

    [HttpPut("{id}")]
    public async Task<IActionResult> PutMenuItem(int id, Item menuItem)
    {
        if (id != menuItem.ItemId)
        {
            return BadRequest();
        }

        _context.Entry(menuItem).State = EntityState.Modified;

        try
        {
            await _context.SaveChangesAsync();
        }
        catch (DbUpdateConcurrencyException)
        {
            if (!MenuItemExists(id))
            {
                return NotFound();
            }
            else
            {
                throw;
            }
        }

        return NoContent();
    }

    [HttpDelete("{id}")]
    public async Task<IActionResult> DeleteMenuItem(int id)
    {
        var menuItem = await _context.MenuItems.FindAsync(id);
        if (menuItem == null)
        {
            return NotFound();
        }

        _context.MenuItems.Remove(menuItem);
        await _context.SaveChangesAsync();

        return NoContent();
    }

    private bool MenuItemExists(int id)
    {
        return _context.MenuItems.Any(e => e.ItemId == id);
    }
}
