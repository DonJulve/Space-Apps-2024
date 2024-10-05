import OpenAI from "openai/index.mjs";
import fs from "fs";
import path from "path"; 
import { log } from "console";
export async function main (gravedad, pl_name, ano, orb) {
const openai = new OpenAI();
openai.apiKey = "sk-o2xcw1mIlT1fyKFo4WvLqatHR3RkjwLVAC9bS--9kST3BlbkFJD4atLGvzKoaCK8YP9hSMZYoh0ymwv6aVnI2jYTZqAA"
var text
 gravedad = `0.8`
 pl_name = `venus`
 ano = `2013`
 orb = `1.3`


//text
  const response =  await openai.chat.completions.create({
    model: "gpt-4o-mini",
    messages: [
      {
        "role": "system",
        "content": [
          {
            "type": "text",
            "text": `
              Eres un humano viviendo en la primera colonia del planeta ` + pl_name + ` en el siglo 23,
              en un texto corto, al estilo de una bitacora, en no mas de un minuto de lectura, el planeta fue descubierto en` + ano + `
              explica como afecta que la gravedad sea `+ gravedad +` veces que la de la tierra, 
              que los años duran `+ orb +`veces que los de la tierra y como solucionais estos problemas.
            `
          }
        ]
      }
    ]
  });
  text = (response.choices[0].message.content)



async function audio(texto) {
  //Audio
  const speechFile = path.resolve("./speech.mp3");

  const mp3 = await openai.audio.speech.create({
    model: "tts-1",
    voice: "onyx",
    input: texto,
  });
  console.log(speechFile);
  const buffer = Buffer.from(await mp3.arrayBuffer());
  await fs.promises.writeFile(speechFile, buffer);
}
console.log(text)
audio(text);
}