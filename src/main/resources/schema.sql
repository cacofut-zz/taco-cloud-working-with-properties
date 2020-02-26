/*create table if not exists user(
	id identity,
	username varchar(80) not null,
	password varchar(100) not null, 
	enabled boolean not null		
);

create table if not exists authority(
	id identity, 
	name varchar(30)	
);

create table if not exists user_authority(
	id identity, 
	user_id bigint not null,
	authority_id bigint not null		
);

create table if not exists groups(
	id identity,
	name varchar(50)	
);

create table if not exists user_groups(
	id identity,
	user_id bigint not null, 
	groups_id bigint not null	
);

create table if not exists groups_authority(
	id identity,
	groups_id bigint not null,
	authority_id bigint not null
);
  
alter table user_authority
  add foreign key(user_id) references user;

alter table user_authority
  add foreign key(authority_id) references authority;
  
alter table user_groups
  add foreign key(user_id) references user;
  
alter table user_groups
  add foreign key(groups_id) references groups;
  
alter table groups_authority
  add foreign key(groups_id) references groups;
  
alter table groups_authority
  add foreign key(authority_id) references authority;
  */

/*
create table if not exists Ingredient(	
	id identity,
	codigo varchar(4) not null,
	name   varchar(50) not null,
	type   varchar(10) not null
);

create table if not exists Taco(
	id identity, 
 	name varchar(50) not null,
 	createdAt timestamp not null
);

create table if not exists Taco_ingredients(
	taco_id bigint not null,
	ingredient_id bigint not null
);


create table if not exists Taco_Order(
	id identity,
	name varchar(50) not null, 
	street varchar(50) not null,
	city varchar(50) not null,
	state varchar(2) not null,
	zip varchar(10) not null,
	ccNumber varchar(16) not null,
	ccExpiration varchar(5) not null, 
	ccCVV varchar(3) not null, 
	placeAt timestamp not null
);

create table if not exists Taco_Order_Tacos(
	taco_order_id bigint not null,
	taco_id bigint not null
); 


alter table Taco_ingredients
  add foreign key(taco_id) references Taco(id);
  
alter table Taco_ingredients
  add foreign key(ingredient_id) references Ingredient(id);
  
  
  alter table Taco_Order_Tacos
  add foreign key(taco_order_id) references Taco_Order(id);
  
alter table Taco_Order_Tacos
  add foreign key(taco_id) references Taco(id);
 
 */
 /*
SELECT * 
  FROM 
  	INGREDIENT, 
  	TACO_INGREDIENTS, 
  	TACO
 WHERE TACO_INGREDIENTS.INGREDIENT_ID = INGREDIENT.ID
   AND TACO_INGREDIENTS.TACO_ID 	   = TACO.ID;
 */
  
 



