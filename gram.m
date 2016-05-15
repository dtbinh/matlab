function out = gram(A)

out = zeros(size(A));

for i = 1:size(A, 2)
    out(:,i) = A(:,i);
end

end
