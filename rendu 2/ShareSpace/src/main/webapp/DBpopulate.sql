insert into `genre`(value) values
("Drama"),
("Comedy"),
("Fantasy"),
("Romance"),
("Thriller"),
("Action"),
("Adventure"),
("Sci-Fi"),
("Classics"),
("Biography"),
("Crime"),
("Documentary"),
("Horror"),
("Mystery"),
("Musical"),
("Western");

insert into `movie`(title, plot, starRating, rating, director, releaseDate, dvdDate, boxOffice, runtime, studio, cover) values
("Lord of the Rings II - The Two Towers", "Frodo and Samwise press on toward Mordor. 
Gollum insists on being the guide. Can anyone so corrupted by the ring be trusted? Can 
Frodo, increasingly under the sway of the ring, even trust himself? Meanwhile, Aragorn, 
drawing closer to his kingly destiny, rallies forces of good for the battles that must 
come. Director Peter Jackson delivers an amazing second movie that won 2 Academy Awards 
(R)* and earned 6 total nominations including Best Picture. The journey continues. So 
do the astonishing spectacle and splendor.", 4.8, "PG-13", "Peter Jackson", "2002-12-11", "2003-08-25", 
339700, 180, "New Line Cinema", "img/lotrII.jpg"),
("Eternal Sunshine of the Spotless Mind", "The second feature from director Michel Gondry (Human Nature) 
finds the filmmaker reteaming with screenwriter Charlie Kaufman for this off-the-wall romantic comedy. 
Jim Carrey stars as Joel Barish, a man who is informed that his ex-girlfriend Clementine (Kate Winslet)
has had her memories of their relationship erased from her brain via an experimental procedure performed
 by Dr. Mierzwiak (Tom Wilkinson). Not to be outdone, Joel decides to have the same procedure done to 
 himself. As Mierzwiak's bumbling underlings Stan (Mark Ruffalo) and Patrick (Elijah Wood) perform the 
 operation on Joel -- over the course of an evening, in his apartment -- Joel struggles in his own mind to
 save the memories of Clementine from being deleted. Kirsten Dunst, David Cross, and Jane Adams also star.",
 4.6, "R", "Michel Gondry", "2004-05-18", "2004-09-27", 34126138, 108, "Focus Features", "img/ESotSM.jpg"),
("Warcraft", "Legendary Pictures' WARCRAFT, a 3D epic adventure of world-colliding conflict based upon 
Blizzard Entertainment's globally-renowned universe, is directed by Duncan Jones (Moon, Source Code) 
and is written by Charles Leavitt and rewritten by Duncan Jones. The producers are Charles Roven, 
Thomas Tull, Jon Jashni and Alex Gartner. Stuart Fenegan, Jillian Share and Brent O'Connor serve as 
executive producers. Blizzard's Chris Metzen co-produces.", 3.6, "PG-13", "Duncan Jones", "2016-06-10", 
"2016-09-27", 0, 100, "Universal Pictures", "img/warcraftbeginning.jpg"),
("The Revenant", "Inspired by true events, THE REVENANT is an immersive and visceral cinematic experience 
capturing one man's epic adventure of survival and the extraordinary power of the human spirit. In an 
expedition of the uncharted American wilderness, legendary explorer Hugh Glass (Leonardo DiCaprio) is 
brutally attacked by a bear and left for dead by members of his own hunting team. In a quest to survive, 
Glass endures unimaginable grief as well as the betrayal of his confidant John Fitzgerald (Tom Hardy). 
Guided by sheer will and the love of his family, Glass must navigate a vicious winter in a relentless 
pursuit to live and find redemption. THE REVENANT is directed and co-written by renowned filmmaker, Academy 
Award (R) winner Alejandro G. Iñarritu (Birdman, Babel).", 4.5, "R", "Alejandro Gonzalez Iñarittu", 
"2015-12-24","2016-04-18", 0, 156, "20th Century Fox","img/revenant.jpg"),
("There's Someting about Mary", "The Farrelly Brothers set this romantic comedy in their home state of 
Rhode Island. In 1985, when teen-nerd Ted Stroehmann (Ben Stiller) challenges a high-schooler who's 
bullying retarded Warren Jenson (W. Earl Brown), his concern prompts Warren's sister, the dazzling and 
desirable Mary Jenson (Cameron Diaz) to choose Ted as her senior prom date, a fact Ted's pals find hard 
to believe. However, on prom night, Ted gets his penis caught in his zipper, so the much-desired date 
never happens. Living in Providence and waxing nostalgic 13 years later, Ted hires Pat Healy (Matt Dillon) 
to locate Mary, and the creepy private investigator finds her in Miami, where she lives with her 
tan-shriveled roommate Magda (Lin Shaye). After Pat develops a stalker-style fixation on the lovely, 
unattached Mary, he lies to Ted, telling him that she's now an overweight mother confined to a wheelchair. 
Employing professional eavesdropping equipment, Pat gathers a dossier on Mary's life and future plans, 
information that forms the basis for more lies when Pat begins dating her. Meanwhile, Ted learns the truth 
but continues to encounter offbeat obstacles as he accelerates to Miami in hopes of finding happiness with 
his true love.", 4.2, "R", "Bobby & Freddy Farrelly", "1998-07-14", "1999-08-02", 0, 118, "20th Century Fox", 
"img/somethingaboutmary.jpg"),
("Pulp Fiction", "Outrageously violent, time-twisting, and in love with language, Pulp Fiction 
was widely considered the most influential American movie of the 1990s. Director and co-screenwriter
 Quentin Tarantino synthesized such seemingly disparate traditions as the syncopated language of 
 David Mamet; the serious violence of American gangster movies, crime movies, and films noirs mixed up 
 with the wacky violence of cartoons, video games, and Japanese animation; and the fragmented story-telling 
 structures of such experimental classics as Citizen Kane, Rashomon, and La jet?e. The Oscar-winning script 
 by Tarantino and Roger Avary intertwines three stories, featuring Samuel L. Jackson and John Travolta, 
 in the role that single-handedly reignited his career, as hit men who have philosophical interchanges 
 on such topics as the French names for American fast food products; Bruce Willis as a boxer out of a 
 1940s B-movie; and such other stalwarts as Harvey Keitel, Tim Roth, Christopher Walken, Eric Stoltz, 
 Ving Rhames, and Uma Thurman, whose dance sequence with Travolta proved an instant classic.", 4.7, "R", 
 "Quentin Tarantino", "1994-09-22", "1998-05-18", 0, 154, "Miramax Films", "img/pulpfiction.jpg");

insert into `playlist`(playlistname, creator, description, image) values
("Chillance", "jojo la frite", "une playlist pour etre au calme le dimanche après avoir ouvert 
ses cadeaux de noël", "img/chillance.jpg"),
("Awaken", "neurchi", "on va se poser et regarder des memes", "img/awaken.jpg"),
("Classics","ThegrandMaster", "Watching classics and rewatching them! This playlist is made for 
you if you want to enlarge your cinema culture or you are just simply bored out of your 
brain.","img/baw.jpg");

insert into `genre_movies`(genreID, movieID) values
(1,1),
(3,1),
(7,1),
(8,1),
(4,2),
(9,2),
(1,2),
(7,2),
(3,3),
(7,3),
(10,4),
(5,4),
(7,4),
(16,4),
(2,5),
(4,5),
(9,5),
(9,6),
(1,6),
(11,6);

insert into `playlist_movies`(playlistID, movieID) values
(1,2),
(1,3),
(1,5),
(2,1),
(2,4),
(3,1),
(3,4),
(3,6);