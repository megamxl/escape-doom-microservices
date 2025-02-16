import path from "path";
import {promises as fs} from "fs";

const modifyStringInFile = async (filePath: string) => {
    try {
        const fileContent = await fs.readFile(filePath, 'utf-8');
        const modifiedContent = modifyBaseUrlDelimiters(fileContent)
        await fs.writeFile(filePath, modifiedContent, 'utf-8');
        console.log(` modified successfully in ${filePath}`);
    } catch (error) {
        if (error instanceof Error) {
            console.error(`Error processing file ${filePath}: ${error.message}`);
        } else {
            console.error(`An unknown error occurred processing file ${filePath}: ${error}`);
        }
    }
}

const modifyBaseUrlDelimiters = (inputString: string) => {
    const regex = /(baseURL:\s*)'([^']*)'/g; // Matches baseUrl: '...'
    return inputString.replace(regex, `$1\`$2\``); // Replaces with baseUrl: `...`
}

const processDirectory = async (dirPath: string) => {
    try {
        const files = await fs.readdir(dirPath);

        for (const file of files) {
            const filePath = path.join(dirPath, file);
            const stats = await fs.stat(filePath);

            if (stats.isDirectory()) {
                await processDirectory(filePath); // Recursive call for subdirectories
            } else if (filePath.endsWith('.ts') || filePath.endsWith('.js')) { // Filter for .ts or .js files
                await modifyStringInFile(filePath);
            }
        }
    } catch (error) {
        console.error(`Error processing directory ${dirPath}:`, error);
    }
}

const main = async () => {

    const directoryPath = `./app/gen/`;

    try {
        await processDirectory(directoryPath);
        console.log("Finished processing directory");
    } catch (error) {
        if(error instanceof Error){
            console.error("An error occurred in main function: ", error.message);
        } else {
            console.error("An unknown error occurred in main function: ", error);
        }
    }
}

export default main