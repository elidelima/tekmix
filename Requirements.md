# Product Backend Requirements

##  Part1:
### 1. Ability to Create, Read, Update and Delete a product
####  1.1. - Product (need to think about required fields & minumum lengths)
1. Id
2. Name
3. Description
4. Price
5. Category -> a set of categories
   - Many to 1 mapping where category is its own table
6. Timestamp created
7. Timestamp updated
8. Region: us/canada (enum)
9. Manufacturer

#### B - Reading a product:
1. Get a specific product (by id)
2. Get a list of products
   - Search criteria: 
     - Name / Description
     - Category
   - Order by:
     - Price
     - ABC order
   - Region
     - US / Canada (manage by the header)
3. Don't send all the data (exclude timestamps & region) -> DTO
4. Cache get products for performance

#### C - Creating/Updating Product
1. No profanity / expletives
   - Use 3rd party API -> Profanity Filter API
     - Will need an API key 
2. Exception handling for bada data & send message back to the UI

### 2. Testing
#### A - Business wants "thorough testing" -> unit tests
#### B - TDD (Test Driven Development) -> Write your unit tests FIRST then write the minimum amount of code

### 3. Logging
#### A - Will add logging to each endpoint

### 4. Want this project to be database independent
#### B. mySQL, Postgres, Microsoft SQL server (no custom queries)

### 5. Use the latest code standards and paradigms

### 6. Security
#### A. Only logged in users can create/update/delete products
#### B. All users may get products
#### C. JWTs security filter chain








