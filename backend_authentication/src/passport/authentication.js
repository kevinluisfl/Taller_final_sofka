const flash = require('connect-flash/lib/flash');
const passport = require('passport');
const localStrategy = require('passport-local').Strategy;

passport.serializeUser((user, done) => {
    done(null, user.id);
});

passport.deserializeUser(async (id, done) => {
    const user = await User.findById(id);
    done(null, user);
});

const User = require('../models/user');

passport.use(
    'local-signup',
    new localStrategy(
        {
            usernameField: 'email',
            passwordField: 'password',
            passReqToCallback: true,
        },
        async (req, email, password, done) => {
            const user = await User.findOne({ email: email });
            if (user) {
                return done(
                    null,
                    false,
                    req.flash('signupMessage', 'The email is alredy taken')
                );
            } else {
                const newUser = new User();

                newUser.email = email;
                newUser.password = newUser.encryptPass(password);
                await newUser.save();
                done(null, newUser);
            }
        }
    )
);

passport.use(
    'local-signin',
    new localStrategy(
        {
            usernameField: 'email',
            passwordField: 'password',
            passReqToCallback: true,
        },
        async (req, email, password, done) => {
            const user = await User.findOne({ email: email });
            if (!user) {
                return done(
                    null,
                    false,
                    req.flash('signinMessage', 'No user found')
                );
            }
            if (!user.comparePass(password)) {
                return done(
                    null,
                    false,
                    req.flash('signinMessage', 'Incorrect password')
                );
            }
            done(null, user);
        }
    )
);
