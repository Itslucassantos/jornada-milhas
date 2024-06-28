package com.jornadamilhas.services.implement;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConsultaChatGPT {

    private final OpenAiService openAiService;

    public ConsultaChatGPT(@Value("${openai.api.key}") String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException("OpenAI API key is required");
        }
        this.openAiService = new OpenAiService(apiKey);
    }

    public String obterInformacao(String nome) {
        CompletionRequest requisicao = CompletionRequest.builder()
                .model("davinci-002")
                .prompt("Faça um resumo sobre " + nome + " enfatizando o porquê este lugar é incrível. Utilize uma linguagem informal e até 100 caracteres no máximo em cada parágrafo. Crie 2 parágrafos neste resumo.")
                .maxTokens(200)
                .build();

        var resposta = openAiService.createCompletion(requisicao);
        return resposta.getChoices().get(0).getText().trim();
    }

}
