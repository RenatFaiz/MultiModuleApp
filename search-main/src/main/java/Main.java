import util.JdbcTemplate;

public class Main {
    public static void main(String[] args) {
        System.out.println(JdbcTemplate.updateProductName("iphone", 0));
        System.out.println(JdbcTemplate.updateProductName("Микроволновая печь V-HOME P70H20L-KH", 0));
        System.out.println(JdbcTemplate.selectProductsByPrice(0, 5000));
        System.out.println(JdbcTemplate.selectAllProducts());
        System.out.println(JdbcTemplate.getOrdersByDate());
    }
}
