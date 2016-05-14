function out = gram(A)
out = zeros(size(A));
n = size(A);
for i = 1:n(2)
    out(:,i) = A(:,i);
end
