require('dotenv').config();
const express = require('express')
const app = express();
const cors = require('cors');
const userModel = require('./models/user');
const cookieParser = require('cookie-parser');
const jwt = require('jsonwebtoken');
const bcrypt = require('bcrypt');

const port = 3000;

app.use(cors({
    origin: "*", // or the actual frontend IP/domain
    credentials: true
}));  //allows requests from your Android app.
app.use(express.json()); 
app.use(express.urlencoded({ extended: true })); 
app.use(cookieParser());
// Cross-Origin Resource Sharing (CORS) in Express.js is a mechanism 
// that allows web applications running on one domain (origin) to safely access resources from a different domain.



app.get("/", function(req,res){
    res.send("hello Arish 11");
})

// Auth middleware
function authMiddleware(req, res, next) {
    const authHeader = req.headers['authorization'];
    const token = authHeader && authHeader.split(' ')[1];

    if (!token) return res.sendStatus(401); // No token

    jwt.verify(token, process.env.SECRET_KEY, (err, user) => {
        if (err) return res.sendStatus(403); // Invalid token
        req.user = user; // Save decoded user info
        next();
    });
}


app.post("/addUser", async (req, res) => {
    const { email, password } = req.body;
    console.log("Received POST:", req.body);

    if (!email || !password) {
        return res.status(400).json({ status: false, message: "Email and Password are required" });
    }

    try {
        let existingUser = await userModel.findOne({ email });
        if (existingUser) {
            return res.status(400).json({ status: false, message: "User already registered" });
        }

        const salt = await bcrypt.genSalt(10);
        const hashedPassword = await bcrypt.hash(password, salt);

        const newUser = await userModel.create({
            email,
            password: hashedPassword
        });

        const token = jwt.sign({ email: email }, process.env.SECRET_KEY, { expiresIn: "7d" });
        res.cookie("token", token, { httpOnly: true, secure: false });

        return res.json({
            status: true,
            message: "User created successfully",
            data: { email: newUser.email, token: token }
        });
    } catch (err) {
        console.error("Save error:", err);
        res.status(500).json({ status: false, message: "Error adding user" });
    }
});

app.post("/login", async (req, res) => {
    const { email, password } = req.body;
    let user = await userModel.findOne({ email });

    if (!user) {
        return res.status(400).json({ status: false, message: "User not found" });
    }

    const match = await bcrypt.compare(password, user.password);    // Compares the Password from DB for Verification
    if (!match) {
        return res.status(400).json({ status: false, message: "Invalid credentials" });
    }

    const token = jwt.sign({ email: email }, process.env.SECRET_KEY, { expiresIn: "7d" });
    // This JWT contains user info (like email, id).

// It is not password, not hash — completely separate.
    res.cookie("token", token, { httpOnly: true, secure: false });

    res.json({
        status: true,
        message: "Login successful",
        data: { email: user.email, token: token }
    });
});

app.post("/logout", (req, res) => {
    res.clearCookie("token");
    res.status(200).json({ status: true, message: "Logged out", token: "" });
});

app.get("/tasks", authMiddleware, (req, res) => {
    res.json({ message: `Hello ${req.user.email}, here are your tasks.` });
});

 

app.listen(port, () =>{
    console.log(`App running at http://localhost:${port}`);
});




        // const newUser = new userModel({ email, password });
        // await newUser.save();