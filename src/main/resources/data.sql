INSERT INTO screeningstations (`id`, `city`,`plz`,`street`,`name`) VALUES ('0', 'Grieskirchen', '4710', 'Parzer Schulstraße 1', 'Schulzentrum - Raiffeisen Arena, Grieskirchen');

INSERT INTO registrations (`id`,`firstname`, `lastname`, `birthdate`, `gender`, `street`, `street_number`,`plz`, `place`, `phone_number`, `email`, `screening_Station_Id`, `test_Date_Time`) VALUES ('1','Hans','Huber', '2000-02-03', '0', 'Dürerstrasse', '10', '4614', 'Marchtrenk', '06606624983', 'mann@fraum.com', '0', '2021-07-16');

INSERT INTO user (`username`, `password`) VALUES ('admin@admin.at', '$2a$10$/yunEG6y3XJ7/XdHdDMRwOldTly.20wXjTE/xNY3082lU49o4uqja'); -- htlgkr