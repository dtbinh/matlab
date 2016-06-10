n = 100000;
prob = zeros(1, n);

for i = 1:n
    sample = randi([0 1], 1, i);
    trial = sum(sample)/i;
    prob(i) = trial;
end

scatter(1:n, prob)
axis([0 inf 0 1])
refline(0, 0.5)
