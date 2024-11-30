const path = require("path");
const resolve = (arg) => path.resolve(__dirname, arg);

module.exports = {
    webpack: {
        alias: {
            '@core': resolve('src/core'),
            '@app': resolve('src/app'),
            '@shared': resolve('../../shared'),
        },
    },
};