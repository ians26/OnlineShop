# OnlineShop
The OnlineShop project is a basic e-commerce website that allows users to view a list of products, add them to their cart, and complete an order by providing their information.
The backend is built using Java Spring Boot and it provides RESTful endpoints for the frontend to communicate with.

Endpoints:

    /api/customers:
        GET: Retrieves a list of all customers.
        POST: Creates a new customer.

    /api/orders:
        POST: Creates a new order.
        POST /{orderId}/products: Adds a product to the specified order.
        POST /{orderId}: Completes the specified order and sends a confirmation email.
        PUT /{orderId}/products/{productId}: Updates the quantity of the specified product in the specified order.
        DELETE /{orderId}/products/{productId}: Deletes the specified product from the specified order.
        GET /{orderId}/products: Retrieves a list of products in the specified order.
        GET /{orderId}: Retrieves the specified order.

    /api/products:
        GET: Retrieves a list of all products.
