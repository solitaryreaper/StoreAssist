-- MySQL dump 10.13  Distrib 5.5.34, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: storeassist_fmm
-- ------------------------------------------------------
-- Server version	5.5.34-0ubuntu0.12.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `item_info`
--

DROP TABLE IF EXISTS `item_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_info` (
  `name` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `section` varchar(255) DEFAULT NULL,
  `aisle` int(11) DEFAULT NULL,
  `store_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_info`
--

LOCK TABLES `item_info` WRITE;
/*!40000 ALTER TABLE `item_info` DISABLE KEYS */;
INSERT INTO `item_info` VALUES ('Asparagus','Fresh vegetables','Food',0,1),('Broccoli','Fresh vegetables','Food',0,1),('Carrots','Fresh vegetables','Food',0,1),('Cauliflower','Fresh vegetables','Food',0,1),('Celery','Fresh vegetables','Food',0,1),('Corn','Fresh vegetables','Food',0,1),('Cucumbers','Fresh vegetables','Food',0,1),('Lettuce / Greens','Fresh vegetables','Food',0,1),('Mushrooms','Fresh vegetables','Food',0,1),('Onions','Fresh vegetables','Food',0,1),('Peppers','Fresh vegetables','Food',0,1),('Potatoes','Fresh vegetables','Food',0,1),('Spinach','Fresh vegetables','Food',0,1),('Squash','Fresh vegetables','Food',0,1),('Zucchini','Fresh vegetables','Food',0,1),('Tomatoes','Fresh vegetables','Food',0,1),('Apples','Fresh fruits','Food',0,1),('Avocados','Fresh fruits','Food',0,1),('Bananas','Fresh fruits','Food',0,1),('Berries','Fresh fruits','Food',0,1),('Cherries','Fresh fruits','Food',0,1),('Grapefruit','Fresh fruits','Food',0,1),('Grapes','Fresh fruits','Food',0,1),('Kiwis','Fresh fruits','Food',0,1),('Lemons / Limes','Fresh fruits','Food',0,1),('Melon','Fresh fruits','Food',0,1),('Nectarines','Fresh fruits','Food',0,1),('Oranges','Fresh fruits','Food',0,1),('Peaches','Fresh fruits','Food',0,1),('Pears','Fresh fruits','Food',0,1),('Plums','Fresh fruits','Food',0,1),('Bagels','Refrigerated items','Food',1,1),('Chip dip','Refrigerated items','Food',1,1),('Eggs / Fake eggs','Refrigerated items','Food',1,1),('English muffins','Refrigerated items','Food',1,1),('Fruit juice','Refrigerated items','Food',1,1),('Hummus','Refrigerated items','Food',1,1),('Ready-bake breads','Refrigerated items','Food',1,1),('Tofu','Refrigerated items','Food',1,1),('Tortillas','Refrigerated items','Food',1,1),('Breakfasts','Frozen','Food',1,1),('Burritos','Frozen','Food',1,1),('Fish sticks','Frozen','Food',1,1),('Fries / Tater tots','Frozen','Food',1,1),('Ice cream / Sorbet','Frozen','Food',1,1),('Juice concentrate','Frozen','Food',1,1),('Pizza','Frozen','Food',1,1),('Pizza Rolls','Frozen','Food',1,1),('Popsicles','Frozen','Food',1,1),('TV dinners','Frozen','Food',1,1),('Vegetables','Frozen','Food',1,1),('BBQ sauce','Condiments / Sauces','Food',2,1),('Gravy','Condiments / Sauces','Food',2,1),('Hot sauce','Condiments / Sauces','Food',2,1),('Jam / Jelly / Preserves','Condiments / Sauces','Food',2,1),('Peanut Butter','Condiments / Sauces','Food',2,1),('Nutela','Condiments / Sauces','Food',2,1),('Chocolate Spread','Condiments / Sauces','Food',2,1),('Honey','Condiments / Sauces','Food',2,1),('Ketchup / Mustard','Condiments / Sauces','Food',2,1),('Mayonnaise','Condiments / Sauces','Food',2,1),('Pasta sauce','Condiments / Sauces','Food',2,1),('Relish','Condiments / Sauces','Food',2,1),('Salad dressing','Condiments / Sauces','Food',2,1),('Salsa','Condiments / Sauces','Food',2,1),('Soy sauce','Condiments / Sauces','Food',2,1),('Steak sauce','Condiments / Sauces','Food',2,1),('Syrup','Condiments / Sauces','Food',2,1),('Worcestershire sauce','Condiments / Sauces','Food',2,1),('Bouillon cubes','Miscellaneous','Food',3,1),('Cereal','Miscellaneous','Food',3,1),('Coffee / Filters','Miscellaneous','Food',3,1),('Instant potatoes','Miscellaneous','Food',3,1),('Lemon / Lime juice','Miscellaneous','Food',3,1),('Mac & cheese','Miscellaneous','Food',3,1),('Olive oil','Miscellaneous','Food',3,1),('Packaged meals','Miscellaneous','Food',3,1),('Pancake / Waffle mix','Miscellaneous','Food',3,1),('Pasta','Miscellaneous','Food',3,1),('Pickles','Miscellaneous','Food',2,1),('Rice','Miscellaneous','Food',3,1),('Tea','Miscellaneous','Food',3,1),('Vegetable oil','Miscellaneous','Food',3,1),('Vinegar','Miscellaneous','Food',2,1),('Applesauce','Canned','Food',2,1),('Fruit Cups','Canned','Food',2,1),('Packaged Fruit','Canned','Food',2,1),('Raisin','Canned / Dried Fruit','Food',2,1),('Dried Cranberries','Canned / Dried Fruit','Food',2,1),('Baked beans','Canned','Food',2,1),('Canned Beans','Canned','Food',2,1),('Broth','Canned','Food',2,1),('Fruit','Canned','Food',2,1),('Olives','Canned','Food',2,1),('Tinned meats','Canned','Food',2,1),('Tuna / Chicken','Canned','Food',2,1),('Soup / Chili','Canned','Food',2,1),('Tomatoes','Canned','Food',2,1),('Veggies','Canned','Food',2,1),('Basil','Spices / Herbs','Food',3,1),('Black pepper','Spices / Herbs','Food',3,1),('Cilantro','Spices / Herbs','Food',3,1),('Cinnamon','Spices / Herbs','Food',3,1),('Garlic','Spices / Herbs','Food',3,1),('Ginger','Spices / Herbs','Food',3,1),('Mint','Spices / Herbs','Food',3,1),('Oregano','Spices / Herbs','Food',3,1),('Paprika','Spices / Herbs','Food',3,1),('Parsley','Spices / Herbs','Food',3,1),('Red pepper','Spices / Herbs','Food',3,1),('Salt','Spices / Herbs','Food',3,1),('Vanilla extract','Spices / Herbs','Food',3,1),('Butter / Margarine','Dairy','Food',6,1),('Cottage cheese','Dairy','Food',6,1),('Half & half','Dairy','Food',6,1),('Milk','Dairy','Food',6,1),('Sour cream','Dairy','Food',6,1),('Whipped cream','Dairy','Food',6,1),('Yogurt','Dairy','Food',6,1),('Bleu cheese','Cheese','Food',6,1),('Cheddar','Cheese','Food',6,1),('Cottage cheese','Cheese','Food',6,1),('Cream cheese','Cheese','Food',6,1),('Feta','Cheese','Food',6,1),('Goat cheese','Cheese','Food',6,1),('Mozzarella','Cheese','Food',6,1),('Parmesan','Cheese','Food',6,1),('Provolone','Cheese','Food',6,1),('Ricotta','Cheese','Food',6,1),('Sandwich slices','Cheese','Food',6,1),('Swiss','Cheese','Food',6,1),('Bacon / Sausage','Meat','Food',6,1),('Beef','Meat','Food',4,1),('Chicken','Meat','Food',4,1),('Ground beef / Turkey','Meat','Food',4,1),('Ham / Pork','Meat','Food',4,1),('Hot dogs','Meat','Food',4,1),('Lunchmeat','Meat','Food',4,1),('Turkey','Meat','Food',4,1),('Catfish','Seafood','Food',4,1),('Crab','Seafood','Food',4,1),('Lobster','Seafood','Food',4,1),('Mussels','Seafood','Food',4,1),('Oysters','Seafood','Food',4,1),('Salmon','Seafood','Food',4,1),('Shrimp','Seafood','Food',4,1),('Tilapia','Seafood','Food',4,1),('Tuna','Seafood','Food',4,1),('Beer','Beverages','Food',7,1),('Club soda / Tonic','Beverages','Food',7,1),('Champagne','Beverages','Food',7,1),('Gin','Beverages','Food',7,1),('Juice','Beverages','Food',7,1),('Mixers','Beverages','Food',7,1),('Red wine / White wine','Beverages','Food',7,1),('Rum','Beverages','Food',7,1),('SakÃ©','Beverages','Food',7,1),('Soda pop','Beverages','Food',7,1),('Sports drink','Beverages','Food',7,1),('Whiskey','Beverages','Food',7,1),('Vodka','Beverages','Food',7,1),('Bagels / Croissants','Baked Goods','Food',5,1),('Buns / Rolls','Baked Goods','Food',5,1),('Cake / Cookies','Baked Goods','Food',5,1),('Donuts / Pastries','Baked Goods','Food',5,1),('Fresh bread','Baked Goods','Food',5,1),('Pie! Pie! Pie!','Baked Goods','Food',5,1),('Pita bread','Baked Goods','Food',5,1),('Sliced bread','Baked Goods','Food',5,1),('Baking powder / Soda','Baking','Food',5,1),('Bread crumbs','Baking','Food',5,1),('Cake / Brownie mix','Baking','Food',5,1),('Cake icing / Decorations','Baking','Food',5,1),('Chocolate chips / Cocoa','Baking','Food',5,1),('Flour','Baking','Food',5,1),('Shortening','Baking','Food',5,1),('Sugar','Baking','Food',5,1),('Sugar substitute','Baking','Food',5,1),('Yeast','Baking','Food',5,1),('Candy / Gum','Snacks','Food',5,1),('Cookies','Snacks','Food',5,1),('Crackers','Snacks','Food',5,1),('Dried fruit','Snacks','Food',5,1),('Granola bars / Mix','Snacks','Food',5,1),('Nuts / Seeds','Snacks','Food',5,1),('Oatmeal','Snacks','Food',5,1),('Popcorn','Snacks','Food',5,1),('Potato / Corn chips','Snacks','Food',5,1),('Pretzels','Snacks','Food',5,1),('Antiperspirant / Deodorant','Personal Care','Household',8,1),('Bath soap / Hand soap','Personal Care','Household',8,1),('Condoms / Other b.c.','Personal Care','Household',8,1),('Cosmetics','Personal Care','Household',8,1),('Cotton swabs / Balls','Personal Care','Household',8,1),('Facial cleanser','Personal Care','Household',8,1),('Facial tissue','Personal Care','Household',8,1),('Feminine products','Personal Care','Household',8,1),('Floss','Personal Care','Household',8,1),('Hair gel / Spray','Personal Care','Household',8,1),('Lip balm','Personal Care','Household',8,1),('Moisturizing lotion','Personal Care','Household',8,1),('Mouthwash','Personal Care','Household',8,1),('Razors / Shaving cream','Personal Care','Household',8,1),('Shampoo / Conditioner','Personal Care','Household',8,1),('Sunblock','Personal Care','Household',8,1),('Toilet paper','Personal Care','Household',8,1),('Toothpaste','Personal Care','Household',8,1),('Vitamins / Supplements','Personal Care','Household',8,1),('Allergy','Medicine','Household',8,1),('Antibiotic','Medicine','Household',8,1),('Antidiarrheal','Medicine','Household',8,1),('Aspirin','Medicine','Household',8,1),('Antacid','Medicine','Household',8,1),('Band-aids / Medical','Medicine','Household',8,1),('Cold / Flu / Sinus','Medicine','Household',8,1),('Pain reliever','Medicine','Household',8,1),('Prescription pick-up','Medicine','Household',8,1),('Aluminum foil','Kitchen','Household',8,1),('Napkins','Kitchen','Household',8,1),('Non-stick spray','Kitchen','Household',8,1),('Paper towels','Kitchen','Household',8,1),('Plastic wrap','Kitchen','Household',8,1),('Sandwich / Freezer bags','Kitchen','Household',8,1),('Wax paper','Kitchen','Household',8,1),('Air freshener','Cleaning Products','Household',8,1),('Bathroom cleaner','Cleaning Products','Household',8,1),('Bleach / Detergent','Cleaning Products','Household',8,1),('Dish / Dishwasher soap','Cleaning Products','Household',8,1),('Garbage bags','Cleaning Products','Household',8,1),('Glass cleaner','Cleaning Products','Household',8,1),('Mop head / Vacuum bags','Cleaning Products','Household',8,1),('Sponges / Scrubbers','Cleaning Products','Household',8,1),('CDRs / DVDRs','Office Supplies','Household',8,1),('Notepad / Envelopes','Office Supplies','Household',8,1),('Glue / Tape','Office Supplies','Household',8,1),('Printer paper','Office Supplies','Household',8,1),('Pens / Pencils','Office Supplies','Household',8,1),('Postage stamps','Office Supplies','Household',8,1),('Arsenic','Carcinogens','Household',8,1),('Asbestos','Carcinogens','Household',8,1),('Cigarettes','Carcinogens','Household',8,1),('Radionuclides','Carcinogens','Household',8,1),('Vinyl chloride','Carcinogens','Household',8,1),('Automotive','Miscellaneous','Household',8,1),('Batteries','Miscellaneous','Household',8,1),('Charcoal / Propane','Miscellaneous','Household',8,1),('Flowers / Greeting card','Miscellaneous','Household',8,1),('Insect repellent','Miscellaneous','Household',8,1),('Light bulbs','Miscellaneous','Household',8,1),('Newspaper / Magazine','Miscellaneous','Household',8,1),('Baby food','Baby Care','Food',8,1),('Diapers','Baby Care','Household',8,1),('Formula','Baby Care','Household',8,1),('Lotion','Baby Care','Household',8,1),('Baby wash','Baby Care','Household',8,1),('Wipes','Baby Care','Household',8,1),('Cat food / Treats','Pets','Household',8,1),('Cat litter','Pets','Household',8,1),('Dog food / Treats','Pets','Household',8,1),('Flea treatment','Pets','Household',8,1),('Pet shampoo','Pets','Household',8,1),('Organic','Organic','Food',1,1),('Gluten Free','Gluten Free','Food',1,1),('Natural','Natural','Food',1,1),('International','International','Food',1,1),('Coconut Water','Natural','Food',1,1);
/*!40000 ALTER TABLE `item_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `zip` int(11) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES (1,'Fresh Madison Market','703 University Avenue',53715,'Madison','WI');
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-12 22:53:22
