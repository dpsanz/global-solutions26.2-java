# Cidade em Dia de Chuva

## Integrantes

| Nome | RM |
| Daniel Sanchez | 561948 |
| Mateus Duarte | 563662 |
| Guilherme Sasso | 563519 |

**RM do grupo** — 1689129 (561948 + 563662 + 563519)

**Versão escolhida** — Web com Thymeleaf.

## Parâmetros do grupo

| Parâmetro | Valor | Regra aplicada |
| --- | --- | --- |
| P1 | `GS129` | Tabelas `GS129_BAIRRO`, `GS129_TRECHO` e `GS129_LEITURA`. |
| P2 | `12 cm` | Normal de 0 a 5 cm, Atenção de 6 a 11 cm e Alerta a partir de 12 cm. |
| P3 | `0` | Bairro tem o campo obrigatório `areaKm2`, do tipo `Double`, maior que zero. |
| P4 | `1` | A lista de trechos ordena por altitude crescente, com desempate por logradouro. |
| P5 | `1` | Ao excluir um trecho, suas leituras são excluídas em cascata. |

## Forma dos DTOs

| DTO | Forma | Campos |
| --- | --- | --- |
| `BairroDTO` | Classe | `id`, `nome`, `zona`, `areaKm2` |
| `TrechoDTO` | Classe | `id`, `logradouro`, `tipoCalcada`, `altitude`, `bairroId`, `bairroNome` |
| `LeituraDTO` | Classe | `id`, `trechoId`, `dataHora`, `laminaCm`, `status`, `trechoDescricao` |
| `PainelItemDTO` | `record` | `logradouro`, `tipoCalcada`, `altitude`, `ultimaLamina`, `ultimoStatus` |
