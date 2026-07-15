const fs = require('fs');
const path = require('path');
const dir = path.join(__dirname);
const files = fs.readdirSync(dir).filter(f => f.endsWith('.html'));

files.forEach(file => {
    const filePath = path.join(dir, file);
    let content = fs.readFileSync(filePath, 'utf8');
    
    // Fix the broken script tag
    content = content.replace(/<script src=" js\/chatbot\.js><\/script>/g, '<script src="js/chatbot.js"></script>');
    content = content.replace(/<script src="js\/chatbot\.js><\/script>/g, '<script src="js/chatbot.js"></script>');
    
    fs.writeFileSync(filePath, content, 'utf8');
});
