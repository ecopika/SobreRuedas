CREATE TABLE IF NOT EXISTS personatge_ES (ID INT PRIMARY KEY NOT NULL,NOM TEXT NOT NULL,EDAT INT,COLOR_P TEXT,MENJAR_P TEXT,  HOBBY TEXT, IDOL TEXT, ANIMAL TEXT, PASIONS TEXT, HABITS TEXT,NOSOPORTA TEXT,FRASE TEXT,SOMNI TEXT,IMATGE TEXT,GIF TEXT, UNAVIDA TEXT, DUESVIDES TEXT);
CREATE TABLE IF NOT EXISTS mapa (ID INT PRIMARY KEY NOT NULL,  NOMIMG TEXT, NOMIMG2 TEXT);
CREATE TABLE IF NOT EXISTS obstacle (ID INT PRIMARY KEY NOT NULL, POS INT, NOMIMGSOL TEXT,RESPOSTACORRECTE INT, MAP INT,FOREIGN KEY(MAP) REFERENCES mapa(ID) );
CREATE TABLE IF NOT EXISTS resposta (ID TEXT PRIMARY KEY NOT NULL, NOMIMGRES TEXT, OBSTACLE INT,FOREIGN KEY(OBSTACLE) REFERENCES obstacle(ID) );
CREATE TABLE IF NOT EXISTS objectesmapa (ID INT PRIMARY KEY NOT NULL,IMATGE TEXT, PROFUNDITAT INT, X FLOAT, Y FLOAT);
CREATE TABLE IF NOT EXISTS perfil(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NOMPERSONATGE TEXT, DIFICULAT TEXT, PUNTUACIO TEXT);
CREATE TABLE IF NOT EXISTS mapa_object(IDMAPA INT NOT NULL, IDOBJECTE INT NOT NULL,PRIMARY KEY (IDMAPA, IDOBJECTE),FOREIGN KEY(IDMAPA) REFERENCES mapa(ID),FOREIGN KEY(IDOBJECTE) REFERENCES objectesmapa(ID));
CREATE TABLE IF NOT EXISTS factors(IDFACTOR INT PRIMARY KEY NOT NULL, FACT FLOAT);
CREATE TABLE IF NOT EXISTS mapa_factors(IDMAPA INT NOT NULL, IDFACTOR INT NOT NULL,PRIMARY KEY (IDMAPA, IDFACTOR),FOREIGN KEY(IDMAPA) REFERENCES mapa(ID),FOREIGN KEY(IDFACTOR) REFERENCES factors(IDFACTOR));
INSERT INTO personatge_ES (ID,NOM,EDAT,COLOR_P,MENJAR_P,HOBBY,IDOL,ANIMAL,PASIONS,HABITS,NOSOPORTA,FRASE,SOMNI,IMATGE,GIF,UNAVIDA, DUESVIDES) VALUES (0,'Núria',10,'Azul','Lasaña','Básquet y senderismo','Mujer Maravilla','Pantera','pintar','lee cómics de Mortadelo y Filemón','los payasos',"¡No hay nada imposible para mi!",'llegar a ser una gran política y cambiar las cosas','nuriafotograma','movnuria.gif',"unavida","duesvides");
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (0,"noia1",43,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (1,"noia2",44,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (2,"noia3",45,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (3,"noia4",46,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (4,"noi1",47,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (5,"noi2",48,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (6,"noi3",49,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (7,"noi4",50,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (8,"botiga1",30,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (9,"cocheesquerra2",31,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (10,"botiga3",32,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (11,"arbre1",33,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (12,"arbre2",34,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (13,"arbre3",35,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (14,"font",36,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (15,"gat1",40,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (16,"gat2",41,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (17,"gat3",42,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (18,"casa1",37,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (19,"casa2",38,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (20,"barco1",0,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (21,"barco2",1,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (22,"paperera",37,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (23,"cochedreta2",20,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (24,"botiga2",21,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (25,"cochedreta4",22,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (26,"cocheesquerra2",10,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (27,"cocheesquerra3",11,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (28,"cocheesquerra4",12,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (29,"cocheesquerra5",13,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (30,"motoesq",14,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (31,"motoesquerra2",15,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (32,"motodret",23,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (33,"motodreta2",24,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (34,"motodreta3",25,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (35,"bus",26,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (36,"parella1",27,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (37,"metro",27,0.0,0.0);
INSERT INTO mapa (ID,NOMIMG,NOMIMG2)VALUES (0,"mapbuit1","rampa");
INSERT INTO mapa (ID, NOMIMG,NOMIMG2)VALUES (1,"mapa2buit","ascensor");
INSERT INTO mapa (ID, NOMIMG,NOMIMG2)VALUES (2,"mapa3","mapa3sol");
INSERT INTO obstacle (ID, POS, NOMIMGSOL,RESPOSTACORRECTE, MAP)VALUES (0,0,"sol1",3,0);
INSERT INTO obstacle (ID, POS, NOMIMGSOL,RESPOSTACORRECTE, MAP)VALUES (1,0,"sol2",1,1);
INSERT INTO obstacle (ID, POS, NOMIMGSOL,RESPOSTACORRECTE, MAP)VALUES (2,0,"sol3",2,2);
INSERT INTO resposta (ID,NOMIMGRES,OBSTACLE)VALUES ("rampa","rampa",0);
INSERT INTO resposta (ID,NOMIMGRES,OBSTACLE)VALUES ("casa2","casa2",1);
INSERT INTO mapa_object(IDMAPA, IDOBJECTE) VALUES (0,0);
INSERT INTO mapa_object(IDMAPA, IDOBJECTE) VALUES (0,10);
INSERT INTO mapa_object(IDMAPA, IDOBJECTE) VALUES (0,21);
INSERT INTO mapa_object(IDMAPA, IDOBJECTE) VALUES (1,5);
INSERT INTO mapa_object(IDMAPA, IDOBJECTE) VALUES (1,37);
INSERT INTO mapa_object(IDMAPA, IDOBJECTE) VALUES (1,36);
INSERT INTO mapa_object(IDMAPA, IDOBJECTE) VALUES (2,9);
INSERT INTO mapa_object(IDMAPA, IDOBJECTE) VALUES (2,24);
INSERT INTO mapa_object(IDMAPA, IDOBJECTE) VALUES (2,11);
INSERT INTO mapa_object(IDMAPA, IDOBJECTE) VALUES (2,17);
INSERT INTO factors(IDFACTOR,FACT) VALUES(0,0.18);
INSERT INTO factors(IDFACTOR,FACT) VALUES(1,0.75);
INSERT INTO factors(IDFACTOR,FACT) VALUES(2,0.5);
INSERT INTO factors(IDFACTOR,FACT) VALUES(3,0.20);
INSERT INTO factors(IDFACTOR,FACT) VALUES(4,0.75);
INSERT INTO factors(IDFACTOR,FACT) VALUES(5,0.85);
INSERT INTO factors(IDFACTOR,FACT) VALUES(6,0.04);
INSERT INTO factors(IDFACTOR,FACT) VALUES(7,0.3);
INSERT INTO factors(IDFACTOR,FACT) VALUES(8,0.15);
INSERT INTO factors(IDFACTOR,FACT) VALUES(9,0.92);
INSERT INTO factors(IDFACTOR,FACT) VALUES(10,0.25);
INSERT INTO factors(IDFACTOR,FACT) VALUES(11,0.15);
INSERT INTO factors(IDFACTOR,FACT) VALUES(12,0.18);
INSERT INTO factors(IDFACTOR,FACT) VALUES(13,0.75);
INSERT INTO factors(IDFACTOR,FACT) VALUES(14,0.35);
INSERT INTO factors(IDFACTOR,FACT) VALUES(15,0.35);
INSERT INTO factors(IDFACTOR,FACT) VALUES(16,0.27);
INSERT INTO factors(IDFACTOR,FACT) VALUES(17,0.8);
INSERT INTO factors(IDFACTOR,FACT) VALUES(18,0.25);
INSERT INTO factors(IDFACTOR,FACT) VALUES(19,0.55);
INSERT INTO factors(IDFACTOR,FACT) VALUES(20,0.4);
INSERT INTO factors(IDFACTOR,FACT) VALUES(21,4);
INSERT INTO factors(IDFACTOR,FACT) VALUES(22,0.55);
INSERT INTO factors(IDFACTOR,FACT) VALUES(23,-1);
INSERT INTO factors(IDFACTOR,FACT) VALUES(24,0.15);
INSERT INTO factors(IDFACTOR,FACT) VALUES(25,1.5);
INSERT INTO factors(IDFACTOR,FACT) VALUES(26,0.4);
INSERT INTO factors(IDFACTOR,FACT) VALUES(27,0.5);
INSERT INTO factors(IDFACTOR,FACT) VALUES(28,0.5);
INSERT INTO factors(IDFACTOR,FACT) VALUES(29,0.6);
INSERT INTO factors(IDFACTOR,FACT) VALUES(30,0.3);
INSERT INTO factors(IDFACTOR,FACT) VALUES(31,0.2);
INSERT INTO factors(IDFACTOR,FACT) VALUES(32,0.15);
INSERT INTO factors(IDFACTOR,FACT) VALUES(33,1.5);
INSERT INTO factors(IDFACTOR,FACT) VALUES(34,0.65);
INSERT INTO factors(IDFACTOR,FACT) VALUES(35,0.2);
INSERT INTO factors(IDFACTOR,FACT) VALUES(36,0.6);
INSERT INTO factors(IDFACTOR,FACT) VALUES(37,0.6);
INSERT INTO factors(IDFACTOR,FACT) VALUES(38,0.3);
INSERT INTO factors(IDFACTOR,FACT) VALUES(39,0.4);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(0,0);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(0,1);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(0,2);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(0,3);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(0,4);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(0,5);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(0,6);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(0,7);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(0,8);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(0,9);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(0,10);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(0,11);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(1,12);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(1,13);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(1,14);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(1,15);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(1,16);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(1,17);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(1,18);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(1,19);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(1,20);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(1,21);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(1,22);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(1,23);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(2,24);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(2,25);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(2,26);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(2,27);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(2,28);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(2,29);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(2,30);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(2,31);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(2,32);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(2,33);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(2,34);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(2,35);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(2,36);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(2,37);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(2,38);
INSERT INTO mapa_factors(IDMAPA,IDFACTOR) VALUES(2,39);