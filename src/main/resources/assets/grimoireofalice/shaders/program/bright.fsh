#version 120

uniform sampler2D DiffuseSampler;
uniform float brightness;

void main()
{
    vec4 sample = texture2D(DiffuseSampler, vec2(gl_TexCoord[0]));
    gl_FragColor = sample * gl_Color + vec4(brightness, brightness, brightness, 0.0);
}