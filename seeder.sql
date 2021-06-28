USE mobile_eats_db;

# TRUNCATE megit nu_items_categories;
INSERT INTO menu_item_categories(name)
VALUES ('hamburgers'),
       ('tacos');

# TRUNCATE menu_items_types;
INSERT INTO menu_items_types(name)
VALUES ('appetizer'),
       ('side'),
       ('entree');

# TRUNCATE vendors_categories;
INSERT INTO vendor_categories(name)
VALUES ('mexican'),
       ('continental'),
       ('asian');

# TRUNCATE users;
INSERT INTO users (email, first_name, image_url, is_admin, last_name, location, password, username)
VALUES('roland@mobileeats.app', 'Roland', 'https://i.pravatar.cc/300',0,'Valdez', '807 Mission Rd, San Antonio, TX 78210','$2a$12$3kvJ6C69qvhe0bOX/sxhNOP.R0EyJCNfmNaMkXfXKnwLY/LO3lUQO','roland'),
      ('kenyon@mobileeats.app', 'Kenyon', 'https://i.pravatar.cc/300',0,'Luce', '17703 La Cantera Pkwy, San Antonio, TX 78257','$2a$12$L3GusL3a/WRsFEUFdF8Cnuu9yvaqC9kLOY5jFXaVALIIx5/S2Kz9.','kenyon');

# TRUNCATE vendors;
INSERT INTO vendors (description, email, is_open, location, name, password, phone_number, profile_image_url)
VALUES ('We are a taco truck serving North San Antonio', 'taco@mobileeats.app', 1,'17000 W I-10, San Antonio, TX 78257','Roland''s Taco Truck','taco',2105555555,'https://threebestrated.com/images/RickysTacos-SanAntonio-TX-1.jpeg'),
       ('We are a hamburger truck serving Down Town San Antonio', 'humburger@mobileeats.app', 1,'300 Alamo Plaza, San Antonio, TX 78205','Kenyon''s Hamburger Truck','taco',2105555556,'https://threebestrated.com/images/TacosElRegio-SanAntonio-TX.jpeg');

# TRUNCATE menus;
INSERT INTO menus(image_url, name, vendor_id)
VALUES('https://tacotexsa.com/wp-content/uploads/2019/10/Taco-Tex-Dine-In-Menu-final-3.jpg','Roland''s Taco Menu', 1),
      ('https://gsburgers.com/wp-content/uploads/2017/11/Menu1smaller.jpg','Kenyon''s Hamburger Menu', 2);

# TRUNCATE menu_items;
INSERT INTO menu_items(description, image_url, name, type_id, menu_id)
VALUES ('order of six corn tortilla tacos with cilantro and onions','https://s.hdnux.com/photos/67/36/76/14544820/6/ratio3x2_2300.jpg', 'mini tacos',3,1),
       ('hamburger combo with fries and a drink','https://s.hdnux.com/photos/01/04/42/35/17966920/3/ratio3x2_2300.jpg', 'hamburger',3,2);

# TRUNCATE reviews;
INSERT INTO reviews(body, rating, user_id, vendor_id)
VALUES ('really liked the tacos - roland', 5, 1, 1),
       ('really liked the burger - roland', 5, 1, 2),
       ('hated the tacos - kenyon', 1, 2, 1),
       ('hated the burgers - kenyon', 2, 2, 2);;

# TRUNCATE vendor_image;
INSERT INTO vendor_image(image_url, vendor_id)
VALUES ('https://threebestrated.com/images/RickysTacos-SanAntonio-TX-2.jpeg',1),
       ('https://threebestrated.com/images/RickysTacos-SanAntonio-TX-1.jpeg', 1),
       ('https://threebestrated.com/images/TacosElRegio-SanAntonio-TX-1.jpeg',2),
       ('https://threebestrated.com/images/TacosElRegio-SanAntonio-TX-2.jpeg', 2);

# TRUNCATE vendors_categories;
INSERT INTO vendors_categories(category_id, vendor_id)
VALUES ('1','1'),
       ('2','2');

# TRUNCATE followers;
INSERT INTO followers(vendor_id, user_id)
VALUES ('1','1'),
       ('2','1'),
       ('1','2');

# TRUNCATE menu_items_types;
INSERT INTO menu_items_categories(category_id, menu_item_id)
VALUES ('1','2'),
       ('2','1');