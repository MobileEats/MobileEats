USE mobile_eats_db;

INSERT INTO menu_item_categories(name)
VALUES ('Hamburgers'),
       ('Pastas'),
       ('Sandwiches'),
       ('Salads'),
       ('Beverages'),
       ('Desserts'),
       ('Soups'),
       ('Noodles'),
       ('Rice'),
       ('Stir Fry'),
       ('Comfort Food'),
       ('Tacos'),
       ('Other');

INSERT INTO menu_items_types(name)
VALUES ('Appetizers'),
        ('Sides'),
        ('A la carte'),
        ('Drinks'),
        ('Children\'s'),
        ('Brunch'),
        ('Lunch'),
        ('Dinner'),
        ('Snacks'),
        ('Main Course'),
        ('Other');

INSERT INTO vendor_categories(name)
VALUES ('Cafe'),
        ('BBQ'),
        ('American'),
        ('Seafood'),
        ('Chinese'),
        ('Mexican'),
        ('Italian'),
        ('Thai'),
        ('Indian'),
        ('Japanese'),
        ('Korean'),
        ('Greek'),
        ('Vietnamese'),
        ('Cuban'),
        ('Fusion'),
        ('Vegan'),
        ('Vegetarian'),
        ('Other');

INSERT INTO users (email, first_name, image_url, is_admin, last_name, location, password, username)
VALUES('roland@mobileeats.app ', 'Roland','https://cdn.filestackcontent.com/HbeTZH9HTECsOFZlMSBz','', 'Valdez', 'San Antonio, TX ', '$2a$10$0oE.OR0sLE98Fu8z/o6z0e.uJ/yaDEI.79CxG8WGK7hgUOcOj0y7G', 'roland '),
      ('jake@mobileeats.app ', 'Jake', 'https://cdn.filestackcontent.com/Appl6O0XQryvuYn9PlpN', '', 'Chadwell', 'Fort Worth, TX', '$2a$10$zH7iNGvjMRlnLjnV1fWwNO5hV0CLi0mQA90Q4tl.Uk3gb5ZD5/Ky2', 'jake'),
       ('kenyon@mobileeats.app','Kenyon','https://cdn.filestackcontent.com/33C3B0wLQM6qDrKE2uDf','','Luce','Converse, TX','$2a$10$JjFQCrS0WZOkMnMboFqeRuCjo7ACccKrZcBj9qfj4MtpXSFgAUWye','kenyon'),
       ('andrew@mobileeats.app','Andrew','https://cdn.filestackcontent.com/n87ZPIvoTCxZFsVworWQ', '' ,'Walsh' ,'Helotes, TX', '$2a$10$mMOQY5YlPTQq3hKCByPvreryUiq86K8JdzX6p3AOEwCL3Vv0BgLtm','andrew'),
       ( 'hunter@mobileeats.app','Hunter','https://cdn.filestackcontent.com/5e2nbh2QdKkGwwhOS4eS' ,  ''  ,'Parks','Austin, TX' , '$2a$10$dwj/wYUk3S8ceHVv6R4fBOVAgunTTCcn9q/eeCbE4hXGSDJVfyk3O','hunter'),
       ( 'rollie526@yahoo.com','Roland','https://cdn.filestackcontent.com/kTgzdQBHQZ61bWceepnO','', 'Valdez' ,'Castroville, TX' , '$2a$10$tpsug9VeTTXXsXeZd.RvJ.OPYX1jEXAIX0v3d0JudhPBeyeNSh7Ne','rollie');

INSERT INTO vendors (description, email, image_url, is_open, location, name, password, phone_number)
VALUES ('We are a pop up scratch kitchen that specializes solely in the preparation of chicken dishes. Primarily Nashville Style Hot Fried Chicken. #SpreadLoveEatChicken','valdez.roland.23@gmail.com' ,'https://cdn.filestackcontent.com/UIkOEsPDSTCqZh9YfGQS'  ,'', '330 Perch Meadow, San Antonio, Texas 78253, United States' ,'Chi Chi Birds Hot Chicken'   , '$2a$10$lchOLWvonWDvtpPOcqI9Febq1mLEMXc.eCKg6o2BXGQI37c/FD5nO', '2104736377 '),
       ('Our business specializes in making sure all our customers leave with an experience of a life time. We specialize in Mariscos , carnitas and birria.' , 'parks.hunter1992@gmail.com', 'https://cdn.filestackcontent.com/1MRgqGcESMGi7b9dNqnJ' , '', '2924 Culebra Rd San Antonio, TX 78228', 'El Remedio Mariscos y Taqueria' , '$2a$10$prQKf.pCEcDMJwh/N36ZF.w8svd.NuY/JDFp6i9wwvQAyylxz0dU2' , '2106213112'),
       ('Delicisou rice meals, but not just your regular rice meals. It\'s differently Filipino. And Differently Filipino is not something you can read about, it\'s something you have to try and experience. You don\'t have to go elsewhere, its OBERE HERE!' , 'jake.chadwell01@gmail.com', 'https://cdn.filestackcontent.com/BuSzaRllRbiy3aGHuseO' ,'', '1165 8th Ave Fort Worth, TX 76104' , 'Ober Here', '$2a$10$vgKcc5SAKPI.g4wQ2CRDUO3O3nKiphz9be9dPtO66ZVoMUL26opcu',' 6827603904'),
       ('We work hard to deliver San Antonio the most authentic experience of Mexican Street Food. Our specialty is the Pastor on Trompo and you can try on our Huaraches, Cuchillos, Street Tacos & many more.' , 'andrew.jordan.walsh@gmail.com','https://cdn.filestackcontent.com/fjLudmuARXGXRGzlCK1f' ,'' ,'2202 Broadway San Antonio, TX 78233', 'Mister Diablo Food Truck' , '$2a$10$0DNZN8vI4ip3Hk3w9R4Uk.ZUG83oWV55wZ1.LIHYjP7aaBj4EIkaG','2104496502'),
       ('A mobile food trailer specializing in authentic Korean food and flavors. We offer bulgogi from a family recipe passed down from generations that date back to South Korea. Bulgogi is thinly sliced beef marinated in Korean flavors.'  , 'kenyon.luce02@gmail.com' , 'https://cdn.filestackcontent.com/TFZcyRSWW3awhqp7Z4nQ',   '' ,'14530 Roadrunner Way San Antonio, TX 78253 ' , 'Bull Gogi Boys' , '$2a$10$vypafX0XeL/Hgeooj9rk5eND6ykxoPqxDgkWaQwjDhtvJJhSG7q2u', '2108161455' );

INSERT INTO menus(image_url, name, vendor_id)
VALUES('https://tacotexsa.com/wp-content/uploads/2019/10/Taco-Tex-Dine-In-Menu-final-3.jpg','Menu', 1),
      ('https://gsburgers.com/wp-content/uploads/2017/11/Menu1smaller.jpg','Menu', 2),
      ('https://tacotexsa.com/wp-content/uploads/2019/10/Taco-Tex-Dine-In-Menu-final-3.jpg','Menu', 3),
      ('https://gsburgers.com/wp-content/uploads/2017/11/Menu1smaller.jpg','Menu', 4),
      ('https://tacotexsa.com/wp-content/uploads/2019/10/Taco-Tex-Dine-In-Menu-final-3.jpg','Menu', 5);

INSERT INTO menu_items(description, image_url, name, type_id, menu_id)
VALUES ('2 Tenders, Fries, Slaw, Bird Sauce' ,'https://cdn.filestackcontent.com/qodQePIRfCBKETOmmear' , 'The Bird' , 10 , 1 ),
       ('Loaded fry version of The Bird', 'https://cdn.filestackcontent.com/q9YKDC0MRNu93ESkrevd' , 'The Nest',10 , 1 ),
       ('4 tacos with cheese and free consome. Meat choice of Birria or Carnitas','https://cdn.filestackcontent.com/ZYysuDmBTpm3mSEFlW3E','Quesitacos' , 7 , 2 ),
       ('10" flour tortilla. Choice of fillings are cheese, birria, or carnitas','https://cdn.filestackcontent.com/d11xAjN0QSiu6HiV2EAQ' ,'Quesadilla ' , 3 ,2 ),
       ( '4 tacos with no cheese and free consome.  Meat choice of birria or carnitas.','https://cdn.filestackcontent.com/7F1sVzWqS5KQkot0ix6h','Regular Tacos', 3 ,2 ),
       ('Grilled, slow cooked pork butt, sweet glaze' , 'https://cdn.filestackcontent.com/sR2UKynpRSW2N2oCbw0o','Pork BBQ Tocino Bowl' , 3 , 3),
       ( 'Spicy honey milk' ,'https://cdn.filestackcontent.com/KM90CkuuQeqCTOr68iRX ','Honey Shrimp Bowl',  3 ,3 ),
       ('House ground pork, ham, better than the canned stuff ' ,'https://cdn.filestackcontent.com/MZYbvdbkREOX6FDFuFae' , 'Homemade Spam Bowl' , 3 , 3 ),
       ( 'Big quesadilla with a thick homemade tortilla w/ your choice of meat & Monterey cheese ','https://cdn.filestackcontent.com/ERb90iQTS3iJ4ifxtu28','Cuchillo' , 3 , 4),
       ('A face up quesadilla with a thick homemade tortilla w/ refried beans, your choice of meat, Monterey cheese and queso fresco.','https://cdn.filestackcontent.com/EAbcPg6iRVyEpuQxNwLw' ,'Huarache' , 3 , 4),
       ('A small corn quesadilla w/ your choice of meat, grilled cactus & Monterey cheese.','https://cdn.filestackcontent.com/dXli8eiS4m1TidU91Fjg','Azteca', 3 , 4),
       ( '4 tacos' , 'https://cdn.filestackcontent.com/xlRl5WMvQdaJ0iNz1jWr' ,'Korean Street Tacos', 3 , 5),
       ( 'Bowl of thinly sliced meat marinated Korean style','https://cdn.filestackcontent.com/Sdi4FTIxQgqRnC6Z2G83','Bulgogi Bowl' , 3 ,5 ),
       ('Korean Egg Roll','https://cdn.filestackcontent.com/urJOxmxQEKXzABkneaC8','Yaki Mandu', 2 , 5);

INSERT INTO reviews(body, rating, user_id, vendor_id)
VALUES ('I love the chicken sandwich.  It was just the right level of heat.' ,5 ,1 , 1 ),
       ('The birria had an odd flavor.  Not bad but different that what I normally expect.',4 , 4,2 ),
       ('Was driving through Ft Worth and decided to try this out.  The homemade spam was out of this world.' ,5 , 1 ,3),
       ('Had not seen a place that sold huaraches.  They were only ok.  Not enough beans to my liking.',3 ,2 ,4),
       ('Had never had bulgogi before and I don\'t think I will again.',1 ,3 ,5);

INSERT INTO vendor_image(image_url, vendor_id)
VALUES ('https://cdn.filestackcontent.com/ogX0ahM8Qiq4ecYVMbjY',1),
       ('https://cdn.filestackcontent.com/Inx8wGETIGP9Nfylt4nA', 2),
       ('https://cdn.filestackcontent.com/Ce3G0fGMRImTxkRdggDg',3),
       ('https://cdn.filestackcontent.com/hNzNt9UXQTixnSRiYKuw', 4),
       ('https://cdn.filestackcontent.com/i8MJOPWRdGmRaxOu90NM',5);

INSERT INTO followers(vendor_id, user_id)
VALUES (2,1),
       (1,1),
       (5,6),
       (3,2),
       (2,3),
       (4,4),
       (3,5);

# INSERT INTO vendors_categories(category_id, vendor_id)
# VALUES (3,1),
#        (6,2),
#        (18,3),
#        (6,4),
#        (11,5);
#
# INSERT INTO menu_items_categories(category_id, menu_item_id)
# VALUES (13,1),
#        (13,2),
#        (12,3),
#        (13,4),
#        (12,5),
#        (10,6),
#        (10,7),
#        (10,8),
#        (13,9),
#        (13,10),
#        (13,11),
#        (12,12),
#        (10,13),
#        (13,14);