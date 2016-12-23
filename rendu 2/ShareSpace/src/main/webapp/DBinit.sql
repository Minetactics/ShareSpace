SET FOREIGN_KEY_CHECKS=0;

Drop table if exists `genre`;
CREATE TABLE `genre`
(
	idgenre int not null auto_increment,
    primary key (idgenre),
	value varchar(40)
);

Drop table if exists `movie`;
create table `movie`
(
	idmovie int not null auto_increment,
    primary key (idmovie),
	title varchar(100),
    plot varchar(1500),
    starRating double,
    rating varchar(10),
    director varchar(100),
    releaseDate date,
    dvdDate date,
    boxOffice integer,
    runtime integer,
    studio varchar(100),
    cover varchar(255)
);

DROP TABLE IF EXISTS `playlist`;
CREATE TABLE `playlist`
(
	idplaylist int not null auto_increment,
    primary key (idplaylist),
	playlistname varchar(40),
    creator varchar(40),
    description varchar(255),
    image varchar(255)
);
    
drop table if exists `playlist_movies`;
create table `playlist_movies`
(
	idpm int not null auto_increment,
	playlistID int,
    movieID int ,
	primary key (idpm)
);

drop table if exists `genre_movies`;
create table `genre_movies`
(
	idgm int not null auto_increment,
	genreID int ,
    movieID int ,
    primary key (idgm)
);

SET FOREIGN_KEY_CHECKS=1;