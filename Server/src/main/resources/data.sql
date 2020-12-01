insert into user (id, username, password, uuid, islogin) values (1, 'ian','emacs', '7bdba0fe-fe95-4b1c-8247-f2479ee6e380', 0);
insert into user (id, username, password, uuid, islogin) values (2, 'sandy','vim','2c77dafe-1545-432f-b5b1-3a0011cf7036', 0);
insert into user (id, username, password, uuid, islogin) values (3, 'vik','vscode','97489bce-1c85-4ff2-b457-ba53589d12cc', 0);

insert into macronutrient (id, protein, fiber, sugar, unsaturatedfat, saturatedfat, transfat, cholesterol, sodium) values (10, 10.0, 1.0, 0.7, 4.0, 0.9, 0.0, 0.0, 0.004);

insert into fooditem (id, name, category, servingsize, type, calories, macronutrient) values (100, 'tofu', 'GRAIN', 100, '?', 83, 10);

insert into userdailyconsumption (id, user, date) values (1, 1,  '2020-11-30 13:42:44');

insert into userfooditem (id, fooditem, userdailyconsumption, numberofserving, meal, totalcalories) values (1, 100, 1, 3, 'SUPPER', 249);
