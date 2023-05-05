insert INTO PROFILE (ID_PROFILE, NOM, DESCRIPTION) VALUES (0, 'ADMIN', 'Administrator profile') on CONFLICT (id_profile) DO NOTHING;
insert INTO PROFILE (ID_PROFILE, NOM, DESCRIPTION) VALUES (1, 'USER', 'User profile') on CONFLICT (id_profile) DO NOTHING;
insert INTO PROFILE (ID_PROFILE, NOM, DESCRIPTION) VALUES (2, 'MANAGER', 'Manager profile') on CONFLICT (id_profile) DO NOTHING;
insert INTO USERS (id_user,email,matricule,nom,prenom,password,id_profile)
values (0,'admin@gmail.com',
        'D47559', 'Admin',
        'Admin',
        '$2a$10$wgPP8gZluOrlVSh11K8eKO8bSBER1GLjkw3.8CzFawqdb6ZtzKbQ6', 0) on CONFLICT (email) DO NOTHING;
insert INTO USERS (email,matricule,nom,prenom,password,id_profile)
values ('oussama@gmail.com',
        'D47559', 'Oussama',
        'Berhili',
        '$2a$10$wgPP8gZluOrlVSh11K8eKO8bSBER1GLjkw3.8CzFawqdb6ZtzKbQ6', 1) on CONFLICT (email) DO NOTHING;
